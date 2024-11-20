package org.example.microusers.feignClient;

import org.example.microusers.feignConfig.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="api-gateway", configuration = FeignConfig.class)
public interface AuthClient {
    @GetMapping("/cuentas/{id}")
    ResponseEntity<?> getById(@PathVariable Long id);
}