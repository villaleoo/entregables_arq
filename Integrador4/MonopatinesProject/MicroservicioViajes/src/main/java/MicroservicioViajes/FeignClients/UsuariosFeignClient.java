package MicroservicioViajes.FeignClients;

import MicroservicioViajes.DTO.CuentaMPDTO;
import MicroservicioViajes.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MicroservicioUsuario")
public interface UsuariosFeignClient {
    @GetMapping("api/usuarios/{id}")
    UsuarioDTO getUsuarioById(@RequestParam Long id);

    @GetMapping("api/cuentasmp/{id}")
    CuentaMPDTO getCuentaMPById(@RequestParam Long id);
}
