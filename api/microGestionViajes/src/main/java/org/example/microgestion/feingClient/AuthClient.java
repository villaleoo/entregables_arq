package org.example.microgestion.feingClient;
import org.example.microgestion.DTO.externals.account.AccountDTO;
import org.example.microgestion.feignConfig.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="api-gateway", configuration = FeignConfig.class)
public interface AuthClient {
    @GetMapping("/cuentas/{id}")
    ResponseEntity<AccountDTO> getById(@PathVariable Long id);
}