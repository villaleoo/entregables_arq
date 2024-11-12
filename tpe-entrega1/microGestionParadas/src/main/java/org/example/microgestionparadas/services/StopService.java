package org.example.microgestionparadas.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestionparadas.DTO.StopDTO;
import org.example.microgestionparadas.model.Parada;
import org.example.microgestionparadas.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    @Autowired
    StopRepository repository;

    public StopDTO save(StopDTO stop){
        Parada p = new Parada(stop.getName(),stop.getAdress(),stop.getLocation());
        this.repository.save(p);

        return stop;
    }

    public List<Parada> findAll(){
        return this.repository.findAll();
    }

    public Parada findById(Long id){
        Optional<Parada> res = this.repository.findById(id);

        if(res.isPresent()){
            return res.get();
        }
        throw new EntityNotFoundException("No se encontro parada con id: "+id);
    }

    public StopDTO update(Long id, StopDTO stop){
        Optional<Parada> p = this.repository.findById(id);

        if(p.isPresent()){
            Parada update = p.get();
            update.setName(stop.getName());
            update.setAdress(stop.getAdress());
            update.setLocation(stop.getLocation());

            this.repository.save(update);

            return stop;
        }

        throw new EntityNotFoundException("No se encontro parada con id: "+id);
    }

    public StopDTO delete (Long id){
        Optional<Parada> p = this.repository.findById(id);

        if(p.isPresent()){
            Parada delete = p.get();
            this.repository.delete(delete);

            return new StopDTO(delete.getName(), delete.getAdress(), delete.getLocation());
        }

        throw new EntityNotFoundException("No se encontro parada con id: "+id);
    }
}
