package org.example.microviajesgestion.feingClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microUsers")
public interface UserClient {
    @GetMapping("/usuarios/{id}")
    ResponseEntity<?> getById(@PathVariable("id") Long id);

}
