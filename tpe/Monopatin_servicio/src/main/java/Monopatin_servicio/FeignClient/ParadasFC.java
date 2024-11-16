package Monopatin_servicio.FeignClient;

import Monopatin_servicio.Dto.ParadaDTO;
import Monopatin_servicio.Entity.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "estacion_servicio", url = "lb://Estacion_servicio")
public interface ParadasFC {

    @GetMapping("")
    ParadaDTO listarUna(@RequestBody Monopatin m);


}
