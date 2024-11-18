package org.example.microgestion.feingClient;

import org.example.microgestion.DTO.payments.ResponseDebitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name="service-usuarios")
public interface UserClient {
    @GetMapping("/usuarios/{id}")
    ResponseEntity<?> getById(@PathVariable("id") Long id);

    @GetMapping("/pagos/{id}")
    ResponseEntity<?> getByAccountAsociate(@PathVariable Long id);

    @PutMapping("/pagos/{id}/debitar")
    ResponseEntity<?> debitCreditForTravel(@PathVariable Long id, @RequestBody ResponseDebitDTO total);


}
