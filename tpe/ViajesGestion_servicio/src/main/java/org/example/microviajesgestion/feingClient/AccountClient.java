package org.example.microviajesgestion.feingClient;


import org.example.microviajesgestion.DTO.ResponseDebitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="microUsers")
public interface AccountClient {
    @GetMapping("/cuentas/{id}")
    ResponseEntity<?> getById(@PathVariable("id") Long id);
    @PutMapping("/cuentas/{id}/debitar")
    ResponseEntity<?> debitCreditForTravel(@PathVariable Long id, @RequestBody ResponseDebitDTO total);
}
