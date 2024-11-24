package ApiGateway.FeignClients;

import ApiGateway.service.dto.cuentasMP.CuentaMPDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "MicroservicioUsuario")
public interface UsuarioFeingClient {
    @GetMapping("api/cuentasmp/{id}")
    Optional<CuentaMPDTO> getCuentaById(@RequestParam Long id);
}
