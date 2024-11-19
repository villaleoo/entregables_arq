package MicroservicioViajes.FeignClients;

import MicroservicioViajes.DTO.CuentaDTO;
import MicroservicioViajes.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MicroservicioUsuario")
public interface UsuariosFeignClient {
    @GetMapping("usuarios/{id}")
    UsuarioDTO getUsuarioById(@RequestParam Long id);

    @GetMapping("cuentas/{id}")
    CuentaDTO getCuentaById(@RequestParam Long id);
}
