package MicroservicioMonopatin.FeignClients;

import MicroservicioMonopatin.DTO.ReporteMonopatinKMsDTO;
import MicroservicioMonopatin.DTO.ReporteMonopatinPausasDTO;
import MicroservicioMonopatin.DTO.ReporteMonopatinesPorCantViajesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MicroservicioViajes")
public interface ViajeFeignClient {
    @GetMapping("viajes/reporte/KMs")
    Page<ReporteMonopatinKMsDTO> getReporteMonopatinesPorKMs(Pageable pageable);

    @GetMapping("viajes/reporte/pausa")
    Page<ReporteMonopatinPausasDTO> getReporteMonopatinesMantenimiento(Pageable pageable, @RequestParam int pausa);

    @GetMapping("viajes/reporte/viajesPorAnio")
    Page<ReporteMonopatinesPorCantViajesDTO> getReporteMonopatinesPorCantViajes(Pageable pageable, @RequestParam int anio, @RequestParam int cantViajes);
}
