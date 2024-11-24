package MicroservicioMonopatin.Controller;


import MicroservicioMonopatin.DTO.*;
import MicroservicioMonopatin.Service.MonopatinService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatines")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

    @GetMapping("")
    public ResponseEntity<Page<MonopatinDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer size,
                                                     @RequestParam(defaultValue = "patenteMonopatin, asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return new ResponseEntity<>(monopatinService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonopatinDTO> getById(@PathVariable Long id) {
        MonopatinDTO monopatin = monopatinService.getById(id);
        if (monopatin != null)
            return new ResponseEntity<>(monopatin, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody MonopatinDTO monopatin) {
        try {
            monopatinService.add(monopatin);
            return new ResponseEntity<>("Monopatin creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error, ingresaste mal el nombre de una columna " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            monopatinService.delete(id);
            return new ResponseEntity<>("Monopatin eliminado exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("El monopatin con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonopatinDTO> update(@PathVariable Long id, @RequestBody MonopatinDTO monopatin) {
        try {
            monopatinService.update(id, monopatin);
            return new ResponseEntity<>(monopatin, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinesByAttribute(MonopatinDTO request) {
        try {
            return new ResponseEntity<>(monopatinService.getMonopatinesByAttribute(request), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reporte/KMs")
    public ResponseEntity<Page<ReporteMonopatinKMsDTO>> getReporteMonopatinesPorKMs(@RequestParam(defaultValue = "0") Integer page,
                                                                                    @RequestParam(defaultValue = "10") Integer size,
                                                                                    String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(monopatinService.getReporteMonopatinesPorKMs(pageable), HttpStatus.OK);
    }

    @GetMapping("/reporte/pausa")
    public ResponseEntity<Page<ReporteMonopatinPausasDTO>> getReporteMonopatinesPorTiempoConPausas(@RequestParam(defaultValue = "0") Integer page,
                                                                                                   @RequestParam(defaultValue = "10") Integer size,
                                                                                                   @RequestParam int pausa,
                                                                                                   String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(monopatinService.getReporteMonopatinesMantenimiento(pageable, pausa), HttpStatus.OK);
    }

    @GetMapping("/reporte/viajesPorAnio")
    public ResponseEntity<Page<ReporteMonopatinesPorCantViajesDTO>> getReporteMonopatinesPorCantViajes(@RequestParam(defaultValue = "0") Integer page,
                                                                                                       @RequestParam(defaultValue = "10") Integer size,
                                                                                                       @RequestParam int anio,
                                                                                                       @RequestParam int cantViajes,
                                                                                                       String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(monopatinService.getReporteMonopatinesPorCantViajes(pageable, anio, cantViajes), HttpStatus.OK);
    }


    @GetMapping("/reporte/disponibles")
    public ResponseEntity<ReportePorDisponibilidadDTO> getMonopatinByDisponibilidad(@RequestParam(defaultValue = "0") Integer page,
                                                                                    @RequestParam(defaultValue = "10") Integer size,
                                                                                    String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        List<MonopatinDisponibleDTO> enOperacion = monopatinService.getMonopatinByDisponibilidad();
        List<MonopatinDisponibleDTO> enMantenimiento = monopatinService.getMonopatinByEnMantenimiento();

        ReportePorDisponibilidadDTO reporte = new ReportePorDisponibilidadDTO(enOperacion, enMantenimiento);
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }

    @PutMapping("/mantenimiento/{id}")
    public ResponseEntity<String> setEnMantenimiento(@PathVariable Long id) {
        try {
            monopatinService.setEnMantenimiento(id);
            return new ResponseEntity<>("Mantenimiento actualizado", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/disponibilidad/{id}")
    public ResponseEntity<?> setDisponibilidad(@PathVariable Long id) {
        try {
            monopatinService.setDisponibilidad(id);
            return new ResponseEntity<>("Disponibilidad actualizada", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
