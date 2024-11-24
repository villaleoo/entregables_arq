package MicroservicioViajes.FeignClients;

import MicroservicioViajes.DTO.CuentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ApiGateway", configuration = MicroservicioViajes.FeignConfig.FeignConfig.class)
public interface CuentaFeignClient {
    @GetMapping("api/cuentas/{id}")
    CuentaDTO getCuentaById(@RequestParam Long id);
}
