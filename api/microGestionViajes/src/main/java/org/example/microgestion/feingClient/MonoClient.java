package org.example.microgestion.feingClient;

import org.example.microgestion.DTO.externals.monopatin.MonopatinDTO;
import org.example.microgestion.DTO.externals.monopatin.UpdateMonoDTO;
import org.example.microgestion.feignConfig.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="service-monopatines", configuration = FeignConfig.class)
public interface MonoClient {
    @GetMapping("/monopatines/{id}")
    ResponseEntity<MonopatinDTO> getById(@PathVariable String id);

    @PutMapping("/monopatines/{id}")
    ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateMonoDTO mono);
}
