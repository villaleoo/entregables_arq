package org.example.microgestion.feingClient;

import org.example.microgestion.DTO.externals.stops.StopDTO;
import org.example.microgestion.feignConfig.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="service-paradas", configuration = FeignConfig.class)
public interface StopClient {
    @GetMapping("/paradas/{id}")
    ResponseEntity<StopDTO> getById(@PathVariable Long id);
}
