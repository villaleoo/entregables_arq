package org.example.apigateway.feignClient;

import org.example.apigateway.DTO.account.RechargeDTO;
import org.example.apigateway.DTO.externals.NewPaymentDTO;
import org.example.apigateway.feignConfig.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@FeignClient(name="service-usuarios", configuration = FeignConfig.class)
public interface PaymentClient {

    @PostMapping("/pagos/")
    ResponseEntity<?> addPayment(@RequestBody NewPaymentDTO newPayment);

    @GetMapping("/pagos/{id}")
    ResponseEntity<?> getById(@PathVariable UUID id);

    @DeleteMapping("/pagos/{id}")
    ResponseEntity<?> delete(@PathVariable Long id);

    @PutMapping("/pagos/recargar/{id}")
    ResponseEntity<?> recharge(@PathVariable Long id, @RequestBody RechargeDTO c);

}
