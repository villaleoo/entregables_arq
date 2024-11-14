package MicroservicioViajes.FeignClients;

import MicroservicioViajes.DTO.MonopatinDTO;
import MicroservicioViajes.DTO.ParadaDTO;
import MicroservicioViajes.DTO.TarifaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name = "MicroservicioMonopatin")
public interface MonopatinFeignClient {
    @GetMapping("monopatines/{id}")
    MonopatinDTO getMonopatinById(@RequestParam Long id);

    @GetMapping("paradas/{id}")
    ParadaDTO getParadaById(@RequestParam Long id);
}


