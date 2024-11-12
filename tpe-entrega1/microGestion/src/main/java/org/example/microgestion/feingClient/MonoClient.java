package org.example.microgestion.feingClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microMonopatines")
public interface MonoClient {
    @GetMapping("/monopatines/{id}")
    ResponseEntity<?> getById(@PathVariable("id") String id);
}
