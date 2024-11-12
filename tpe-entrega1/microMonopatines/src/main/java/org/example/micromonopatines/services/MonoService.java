package org.example.micromonopatines.services;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.micromonopatines.DTO.*;
import org.example.micromonopatines.DTO.externals.IdTravelDTO;
import org.example.micromonopatines.DTO.externals.ResponseDebitDTO;
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
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.List;
import java.util.Map;
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

        System.out.println(mono);
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

    public Object updateLocationInTravel(String id, LocationDTO location){
        Optional<Monopatin> result = this.repository.findById(id);
        String idTravelActive=this.getIdTravelActiveOfMono(id);

        if(result.isPresent() && idTravelActive != null){
            Monopatin m = result.get();
            double distance = calculateDistance(m.getLocation(), location.getLocation());

            if(distance >= 1.0){
                double currentKms=m.getKms()+distance;                  /*el nuevo kilometraje es lo que tenia+la distancia que hizo en kms*/
                m.setLocation(location.getLocation());                  /*actualiza la ubicacion del monopatin*/
                m.setKms(currentKms);                                   /*le agrega los kms hechos->simulados*/
                this.repository.save(m);


                return this.updateKmsAndDebitMoney(idTravelActive,currentKms);             /*le avisa al ticket de viaje activo sus nuevos kms*/

            }else{
                return new AlreadyUpdateKmsDTO(m.getLocation(),m.getKms()); /*si la distancia es de menos de 1km no se modifica la ubicacion (es muy pequeña)*/
            }

        }
        if(idTravelActive == null){
            throw new NotFoundEntityException("No se encontro viaje activo para monopatin: "+id);
        }

        throw new MonopatinNotFoundException("No se encontro monopatin con id: "+id);
    }


    private ResponseDebitDTO updateKmsAndDebitMoney(String idTravel, double currentKms){
        ResponseEntity<?> res = this.travelClient.updateKmsAndDebitMoney(idTravel,new NewKmsDTO(currentKms));

        if(res.getStatusCode().is2xxSuccessful() && res.getBody() != null){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(res.getBody(), ResponseDebitDTO.class);

        }
        throw new NotFoundEntityException("Fallo al actualizar kilometraje en el ticket de viaje.");
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

    private double calculateDistance(Point point1, Point point2) {
        final double EARTH_RADIUS = 6371.0;

        // Convertir las coordenadas de grados a radianes
        double lat1 = Math.toRadians(point1.getY()); // Asegúrate de que esto es latitud
        double lon1 = Math.toRadians(point1.getX()); // Y longitud
        double lat2 = Math.toRadians(point2.getY()); // Asegúrate de que esto es latitud
        double lon2 = Math.toRadians(point2.getX()); // Y longitud

        // Diferencias entre latitudes y longitudes
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Fórmula de Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia en kilómetros
        double distance = EARTH_RADIUS * c;

        return distance/20;
    }
}
