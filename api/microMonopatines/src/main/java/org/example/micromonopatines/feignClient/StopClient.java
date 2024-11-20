package org.example.micromonopatines.feignClient;

import org.example.micromonopatines.DTO.externals.StopDTO;
import org.example.micromonopatines.feignConfig.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="service-paradas", configuration = FeignConfig.class)
public interface StopClient {
    @GetMapping("/paradas/{id}")
    ResponseEntity<StopDTO> findById(@PathVariable Long id);
}
