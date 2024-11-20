package org.example.microgestion.feingClient;

import org.example.microgestion.DTO.externals.account.UsuarioDTO;
import org.example.microgestion.DTO.payments.PaymentDTO;
import org.example.microgestion.DTO.payments.ResponseDebitDTO;
import org.example.microgestion.feignConfig.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name="service-usuarios", configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/usuarios/{id}")
    ResponseEntity<UsuarioDTO> getById(@PathVariable("id") Long id);

    @GetMapping("/pagos/{id}")
    ResponseEntity<PaymentDTO> getByAccountAsociate(@PathVariable Long id);

    @PutMapping("/pagos/{id}/debitar")
    ResponseEntity<ResponseDebitDTO> debitCreditForTravel(@PathVariable Long id, @RequestBody ResponseDebitDTO total);


}
