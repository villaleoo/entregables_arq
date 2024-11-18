package org.example.microgestionparadas.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.microgestionparadas.DTO.*;
import org.example.microgestionparadas.feignClient.MonoClient;
import org.example.microgestionparadas.model.Parada;
import org.example.microgestionparadas.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    @Autowired
    StopRepository repository;

    @Autowired
    MonoClient monoClient;

    public VisibleDataStopDTO save(VisibleDataStopDTO stop){
        Parada p = new Parada(stop.getName(),stop.getAdress(),stop.getLocation());
        this.repository.save(p);

        return stop;
    }

    public List<Parada> findAll(){
        return this.repository.findAll();
    }

    public StopDTO findById(Long id){
        Optional<Parada> res = this.repository.findById(id);

        if(res.isPresent()){
            Parada p =res.get();
            Point location = new Point((int)p.getX(),(int)p.getY());
            return new StopDTO(p.getId_stop(),p.getName(),p.getAdress(),location);
        }
        throw new EntityNotFoundException("No se encontro parada con id: "+id);
    }

    public VisibleDataStopDTO update(Long id, VisibleDataStopDTO stop){
        Optional<Parada> p = this.repository.findById(id);

        if(p.isPresent()){
            Parada update = p.get();
            update.setName(stop.getName());
            update.setAdress(stop.getAdress());
            update.setY(stop.getLocation().getY());
            update.setX(stop.getLocation().getX());

            this.repository.save(update);

            return stop;
        }

        throw new EntityNotFoundException("No se encontro parada con id: "+id);
    }

    public VisibleDataStopDTO delete (Long id){
        Optional<Parada> p = this.repository.findById(id);

        if(p.isPresent()){
            Parada delete = p.get();
            this.repository.delete(delete);
            Point location = new Point((int)delete.getX(),(int) delete.getY());

            return new VisibleDataStopDTO(delete.getName(), delete.getAdress(), location);
        }

        throw new EntityNotFoundException("No se encontro parada con id: "+id);
    }

    public ArrayList<StopAvailabilityDTO> findMostNear(Integer x,Integer y){
        Pageable pageRequest = PageRequest.of(0, 5);
        List<Parada> res = this.repository.findTopMostNear(x, y,pageRequest );

        if(!res.isEmpty()){
            return getReportAvailability(res);
        }else{
            return  new ArrayList<>();
        }

    }

    private ArrayList<StopAvailabilityDTO> getReportAvailability(List<Parada> list){
        ArrayList<StopAvailabilityDTO> res =new ArrayList<>();

        for(Parada p :list){
            System.out.println(p.getId_stop());
            Integer qMonopatines=this.findQuantityAvailableMonopatines(p.getId_stop());
            Point location = new Point((int)p.getX(),(int)p.getY());
            res.add(new StopAvailabilityDTO(p.getName(),p.getAdress(),location,qMonopatines));
        }

        return res;
    }

    private Integer findQuantityAvailableMonopatines(Long idStop){
        ResponseEntity<?> res = this.monoClient.getAllInStop(idStop);

        if(res.getStatusCode().is2xxSuccessful() && res.getBody() !=null){
            ObjectMapper objectMapper = new ObjectMapper();
            List list =objectMapper.convertValue(res.getBody(), List.class);

            return list.size();
        }

        throw new EntityNotFoundException("Error al obtener los monopatines disponibles en parada.");

    }
}
