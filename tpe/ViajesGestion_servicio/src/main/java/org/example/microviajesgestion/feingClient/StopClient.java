package org.example.microviajesgestion.feingClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microGestionParadas")
public interface StopClient {
    @GetMapping("/paradas/{id}")
    ResponseEntity<?> getById(@PathVariable Long id);
}
