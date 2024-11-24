package MicroservicioViajes.FeignClients;

import MicroservicioViajes.DTO.MonopatinDTO;
import MicroservicioViajes.DTO.ParadaDTO;
import MicroservicioViajes.DTO.TarifaDTO;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;

@FeignClient(name = "MicroservicioMonopatin")
public interface MonopatinFeignClient {
    @GetMapping("api/monopatines/{id}")
    MonopatinDTO getMonopatinById(@RequestParam Long id);

    @GetMapping("api/paradas/{id}")
    ParadaDTO getParadaById(@RequestParam Long id);

    @Modifying
    @Transactional
    @PutMapping("api/monopatines/disponibilidad/{id}")
    ResponseEntity<String> setDisponibilidad(@RequestParam Long id);

    @PutMapping("api/paradas/{id}")
    void updateParada(@RequestParam Long id, @RequestBody ParadaDTO parada);

    @PutMapping("api/paradas/agregarMonopatin/{id}")
    void addMonoAParada(@PathVariable("id") Long idParada, @RequestBody MonopatinDTO monopatinDTO);

    @PutMapping("api/monopatines/{id}")
    void updateMonopatin(@PathVariable("id") Long id, @RequestBody MonopatinDTO monopatinDTO);
}


