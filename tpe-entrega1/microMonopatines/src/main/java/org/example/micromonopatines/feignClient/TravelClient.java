package org.example.micromonopatines.feignClient;

import org.example.micromonopatines.DTO.kms.DistanceTraveledDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="microGestion")
public interface TravelClient {
    @GetMapping("/viajes/en-curso/monopatin/{id}")
    ResponseEntity<?> getIdTravelInProgressOfMonopatin(@PathVariable String id);  /*este retorna el id del viaje en curso*/

    @PutMapping("/viajes/{id}/total-kilometros")
    ResponseEntity<?> updateTotalKmsInTravel(@PathVariable String id, @RequestBody DistanceTraveledDTO newKmsMono);     /*cuando se ejecuta este en viajes,actualizo el total de plata del viaje y mando a descontar de la cuenta*/

    @GetMapping("/viajes/informe/monopatines")
    ResponseEntity<?> getAllMonoWithTravels(@RequestParam("anio") Integer anio, @RequestParam("minViaje") Integer minViaje);

    @GetMapping("/viajes/acumulado")
    ResponseEntity<?> getDetailsTravelsMonopatin(@RequestParam("monopatin") String idMono);

}
