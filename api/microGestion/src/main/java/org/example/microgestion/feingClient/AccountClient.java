package org.example.microgestion.feingClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="api-gateway")
public interface AccountClient {

    @GetMapping("/cuentas/{id}")
    ResponseEntity<?> getById(@PathVariable Long id);
}
