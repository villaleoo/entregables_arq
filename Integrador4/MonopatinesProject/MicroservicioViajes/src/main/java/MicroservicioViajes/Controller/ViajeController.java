package MicroservicioViajes.Controller;


import MicroservicioViajes.DTO.*;
import MicroservicioViajes.Service.ViajeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @GetMapping("")
    public ResponseEntity<Page<ViajeDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size,
                                                 @RequestParam(defaultValue = "fechaInicio, asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return new ResponseEntity<>(viajeService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeDTO> getById(@PathVariable Long id) {
        ViajeDTO viaje = viajeService.getById(id);
        if (viaje != null)
            return new ResponseEntity<>(viaje, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<String> iniciarViaje(@RequestBody IniciarViajeDTO viaje) {
        try {
            viajeService.iniciarViaje(viaje);
            return new ResponseEntity<>("Viaje creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/pausar/{id}")
    public ResponseEntity<String> pausarViaje(@PathVariable Long id) {
        try {
            viajeService.pausarViaje(id);
            return new ResponseEntity<>("Viaje pausado.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reanudar/{id}")
    public ResponseEntity<String> reanudarViaje(@PathVariable Long id) {
        try {
            viajeService.reanudarViaje(id);
            return new ResponseEntity<>("Viaje reanudado.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/terminar/{id}")
    public ResponseEntity<?> terminarViaje(@PathVariable Long id, @RequestBody TerminarViajeDTO terminarViajeDTO) {
        try {
            return new ResponseEntity<>(viajeService.terminarViaje(id, terminarViajeDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            viajeService.delete(id);
            return new ResponseEntity<>("Viaje eliminado exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("El viaje con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViajeDTO> update(@PathVariable Long id, @RequestBody ViajeDTO viaje) {
        try {
            viajeService.update(id, viaje);
            return new ResponseEntity<>(viaje, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reporte/KMs")
    public ResponseEntity<Page<ReporteMonopatinKMsDTO>> getReporteMonopatinesPorKMs(@RequestParam(defaultValue = "0") Integer page,
                                                                                    @RequestParam(defaultValue = "10") Integer size,
                                                                                    String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(viajeService.getReporteMonopatinesPorKMs(pageable), HttpStatus.OK);
    }

   @GetMapping("/reporte/pausa")
    public ResponseEntity<Page<ReporteMonopatinPausasDTO>> getReporteMonopatinesPorTiempoConPausas(@RequestParam(defaultValue = "0") Integer page,
                                                                                                   @RequestParam(defaultValue = "10") Integer size,
                                                                                                   @RequestParam int pausa,
                                                                                                   String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(viajeService.getReporteMantenimiento(pageable, pausa), HttpStatus.OK);
    }


    @GetMapping("/reporte/viajesPorAnio")
    public ResponseEntity<Page<ReporteMonopatinesPorCantViajesDTO>> getReporteMonopatinesPorCantViajes(@RequestParam(defaultValue = "0") Integer page,
                                                                                                       @RequestParam(defaultValue = "10") Integer size,
                                                                                                       @RequestParam int anio,
                                                                                                       @RequestParam int cantViajes,
                                                                                                       String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(viajeService.getReporteMonopatinesPorCantViajes(pageable, anio, cantViajes), HttpStatus.OK);
    }

    @GetMapping("/reporte/facturado")
    public ResponseEntity<Page<ReporteFacturadoPorRangoDTO>> getReporteFacturadoEnAnioYMeses(@RequestParam(defaultValue = "0") Integer page,
                                                                                             @RequestParam(defaultValue = "10") Integer size,
                                                                                             @RequestParam int anio,
                                                                                             @RequestParam int mesInicio,
                                                                                             @RequestParam int mesFin,
                                                                                             String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(viajeService.getReporteFacturadoEnAnioYMeses(pageable, anio, mesInicio, mesFin), HttpStatus.OK);
    }


}
