package org.example.microusers.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="api-gateway")
public interface AuthClient {
    @GetMapping("/cuentas/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);
}