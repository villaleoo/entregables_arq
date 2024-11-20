package org.example.micromonopatines.services;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.micromonopatines.DTO.*;
import org.example.micromonopatines.DTO.externals.IdTravelDTO;
import org.example.micromonopatines.DTO.externals.StopDTO;
import org.example.micromonopatines.DTO.externals.TotalTravelsDTO;
import org.example.micromonopatines.DTO.location.KmsLocationDTO;
import org.example.micromonopatines.DTO.location.LocationDTO;
import org.example.micromonopatines.DTO.reports.*;
import org.example.micromonopatines.exceptions.MonopatinNotFoundException;
import org.example.micromonopatines.exceptions.NotFoundEntityException;
import org.example.micromonopatines.exceptions.ParadaNotFoundException;
import org.example.micromonopatines.feignClient.StopClient;
import org.example.micromonopatines.feignClient.TravelClient;
import org.example.micromonopatines.model.Monopatin;
import org.example.micromonopatines.repository.MonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonoService {

    @Autowired
    MonoRepository repository;
    @Autowired
    StopClient stopClient;
    @Autowired
    TravelClient travelClient;

    public MonoDTO save(MonoDTO mono){
        ResponseEntity<?> res = this.stopClient.findById(mono.getId_stop());

        if(res.getStatusCode().is2xxSuccessful() && res.getBody() !=null){
            ObjectMapper objectMapper = new ObjectMapper();
            StopDTO stop = objectMapper.convertValue(res.getBody(), StopDTO.class);

            Monopatin m = new Monopatin(mono.isAvailable(),stop.getLocation(),mono.getKms(),stop);
            this.repository.save(m);
            return mono;
        }

        throw new ParadaNotFoundException("No se encontro la parada con id:"+mono.getId_stop());
    }

    public Monopatin findById(String id){
        Optional<Monopatin> res = this.repository.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        throw new MonopatinNotFoundException("No se encontro monopatin con id: "+id);
    }

    public List<Monopatin> findAll(){
        return this.repository.findAll();
    }

    public List<ReportTravelInYearDTO> findByTravelAndYear(Integer year, Integer minTravel){
        ResponseEntity<?> res = this.travelClient.getAllMonoWithTravels(year,minTravel);

        if (res.getStatusCode().is2xxSuccessful() && res.getBody() != null){
            ObjectMapper objectMapper = new ObjectMapper();

            List<ReportTravelInYearDTO> list = objectMapper.convertValue(res.getBody(), List.class);

            return list;
        }

        throw new NotFoundEntityException("No se pudo generar la lista.");
    }

    public UpdateMonoDTO update(String id, UpdateMonoDTO mono){
        Optional<Monopatin> result = this.repository.findById(id);

        if(result.isPresent()){
            Monopatin m = result.get();
            m.setStop_assign(mono.getStopAssign());
            m.setLocation(mono.getStopAssign().getLocation());
            m.setAvailable(mono.isAvailable());


            this.repository.save(m);

            return mono;
        }

        throw new MonopatinNotFoundException("No se encontro monopatin con id: "+id);
    }

    public Monopatin delete(String id){
        Optional<Monopatin> result = this.repository.findById(id);

        if(result.isPresent()){
            Monopatin m = result.get();
            this.repository.delete(m);

            return m;
        }

        throw new MonopatinNotFoundException("No se encontro monopatin con id: "+id);
    }

    public AvailableDTO findAvailableReport(){
        List<Monopatin> available = this.repository.countAvailable();
        List<Monopatin> notAvailable = this.repository.countNotAvailable();

        return new AvailableDTO((long) available.size(),(long) notAvailable.size());
    }


    public List<Monopatin> findAvailableInStop(Long id){
        List<Monopatin> res = this.repository.findAllInStopAndAvailable(id);
        ArrayList<Monopatin> results=new ArrayList<>();

        if(!res.isEmpty()){

            for(Monopatin mono: res){
                if(this.getIdTravelActiveOfMono(mono.getId_monopatin()) == null){
                    results.add(mono);
                }
            }

        }

        return results;
    }

    public Object generateUsageReport(String id,Boolean includePause){
        TotalTravelsDTO res=this.getDetailsTravels(id);
        Optional<Monopatin> m = this.repository.findById(id);

        if(m.isPresent()){
            Monopatin mono=m.get();
            if(res==null){
                return new NotUsageDTO(id,(long)mono.getKms(),"El monopatin no tiene viajes efectuados.");
            }
            if(includePause==null){
                return new ReportUsageDTO(id,(long)mono.getKms(),res.getTotal_min_traveled(),res.getTotal_travels());
            }
            return new ReportUsagePauseDTO(id,(long)mono.getKms(), res.getTotal_min_traveled(), res.getTotal_travels(),res.getTotal_min_pause());
        }

        throw new MonopatinNotFoundException("No se econtro monopatin con id: "+id);
    }


    private TotalTravelsDTO getDetailsTravels(String id){
        ResponseEntity<?> res = this.travelClient.getDetailsTravelsMonopatin(id);

        if(res.getStatusCode().is2xxSuccessful() && res.getBody()!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(res.getBody(), TotalTravelsDTO.class);
        }

        return null;
    }


    private String getIdTravelActiveOfMono(String id){
        ResponseEntity<?> res = this.travelClient.getIdTravelInProgressOfMonopatin(id);

        if(res.getStatusCode().is2xxSuccessful() && res.getBody() != null){
            ObjectMapper objectMapper = new ObjectMapper();
            IdTravelDTO dto =objectMapper.convertValue(res.getBody(), IdTravelDTO.class);
            return dto.getId_travel();
        }

        return null;
    }


    public KmsLocationDTO updateLocationInTravel(String id, LocationDTO location){
        Optional<Monopatin> result = this.repository.findById(id);
        String idTravelActive=this.getIdTravelActiveOfMono(id);

        if(idTravelActive == null){
            throw new NotFoundEntityException("No se encontro viaje activo para monopatin: "+id);
        }

        if(result.isEmpty()){
            throw new MonopatinNotFoundException("No se encontro monopatin con id: "+id);
        }



        Monopatin m = result.get();
        double distanceInKms = calculateDistance(m.getLocation(), location.getLocation());


        double currentKms=m.getKms()+distanceInKms;              /*el nuevo kilometraje es lo que tenia+la distancia que hizo en kms*/
        m.setLocation(location.getLocation());                  /*actualiza la ubicacion del monopatin*/
        m.setKms(currentKms);                                   /*le agrega los kms hechos->simulados*/
        this.repository.save(m);

        return new KmsLocationDTO(location.getLocation(),currentKms);
    }


    public UpdateMonoDTO updateAvailable(String id){
        Optional<Monopatin> res =this.repository.findById(id);
        if(res.isEmpty()){
            throw new RuntimeException("No existe monopatin con id: "+id);
        }

        Monopatin mono = res.get();
        mono.setAvailable(!mono.isAvailable());
        this.repository.save(mono);

        return new UpdateMonoDTO(mono.isAvailable(),mono.getStop_assign());
    }


    private double calculateDistance(Point currentPos, Point newPos) {
        final double SIMULATE_INCREMENT_KMS = 0.5;
        /*esto quiere decir que cuando se avanza en 1 de x o de y el monopatin recorre 0,5kms*/

        double x1 = currentPos.getX();
        double y1 = currentPos.getY();
        double x2 = newPos.getX();
        double y2 = newPos.getY();

        double dx = Math.abs(x1-x2);
        double dy = Math.abs(y1-y2);


        return (dx+dy)*SIMULATE_INCREMENT_KMS;
    }
}
