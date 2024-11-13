package org.example.microgestionparadas.feignClient;


import org.example.microgestionparadas.DTO.LocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="microMonopatines")
public interface MonoClient {
    @GetMapping("/monopatines/en-parada")
    ResponseEntity<?> getAllInStop(@RequestParam("idParada") Long id);
}