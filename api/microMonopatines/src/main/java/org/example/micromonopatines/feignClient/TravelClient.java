package org.example.micromonopatines.feignClient;


import org.example.micromonopatines.DTO.externals.IdTravelDTO;
import org.example.micromonopatines.DTO.externals.TotalTravelsDTO;
import org.example.micromonopatines.feignConfig.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="service-gestion", configuration = FeignConfig.class)
public interface TravelClient {
    @GetMapping("/viajes/en-curso/monopatin/{id}")
    ResponseEntity<IdTravelDTO> getIdTravelInProgressOfMonopatin(@PathVariable String id);  /*este retorna el id del viaje en curso*/

    @GetMapping("/viajes/informe/monopatines")
    ResponseEntity<?> getAllMonoWithTravels(@RequestParam("anio") Integer anio, @RequestParam("minViaje") Integer minViaje);

    @GetMapping("/viajes/acumulado")
    ResponseEntity<TotalTravelsDTO> getDetailsTravelsMonopatin(@RequestParam("monopatin") String idMono);

}
