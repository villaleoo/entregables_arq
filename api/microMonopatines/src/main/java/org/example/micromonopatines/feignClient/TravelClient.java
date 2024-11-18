package org.example.micromonopatines.feignClient;

import org.example.micromonopatines.DTO.kms.DistanceTraveledDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="service-gestion")
public interface TravelClient {
    @GetMapping("/viajes/en-curso/monopatin/{id}")
    ResponseEntity<?> getIdTravelInProgressOfMonopatin(@PathVariable String id);  /*este retorna el id del viaje en curso*/

    @GetMapping("/viajes/informe/monopatines")
    ResponseEntity<?> getAllMonoWithTravels(@RequestParam("anio") Integer anio, @RequestParam("minViaje") Integer minViaje);

    @GetMapping("/viajes/acumulado")
    ResponseEntity<?> getDetailsTravelsMonopatin(@RequestParam("monopatin") String idMono);

}
