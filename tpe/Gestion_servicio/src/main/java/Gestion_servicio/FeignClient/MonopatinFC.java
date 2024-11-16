package Gestion_servicio.FeignClient;

import Gestion_servicio.Dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "monopatin_servicio", url = "http://localhost:8080/monopatines")
public interface MonopatinFC {
    @GetMapping("/{id}")
    MonopatinDTO listarUno(@PathVariable Integer id);

    @GetMapping("/mantenimiento/lista")
    String monopatinesDisponiblesVsMant();
}
