package Monopatin_servicio.Controller;

import Monopatin_servicio.Dto.CoordenadaDTO;
import Monopatin_servicio.Dto.MonopatinDTO;
import Monopatin_servicio.Dto.ParadaDTO;
import Monopatin_servicio.Entity.Monopatin;
import Monopatin_servicio.Service.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monopatines")
public class MonopatinController {
    @Autowired
    private MonopatinService service;
    @GetMapping("")
    public ResponseEntity<List<MonopatinDTO>> listarTodos() {
        return ResponseEntity.ok(this.service.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MonopatinDTO> listarUno(@PathVariable Integer id) {
        return ResponseEntity.ok(this.service.listarUno(id));
    }
    @GetMapping("/mantenimiento/lista")
    public ResponseEntity<List<MonopatinDTO>> listarTodosMantenimiento() {
        return ResponseEntity.ok(this.service.listarTodosMantenimiento());
    }
    @GetMapping("/reporte/tcp")
    public ResponseEntity<List<MonopatinDTO>> reporteTiempoConPausa() {
        return ResponseEntity.ok(this.service.reporteTiempoConPausa());
    }
    @GetMapping("/reporte/tsp")
    public ResponseEntity<List<MonopatinDTO>> reporteTiempoSinPausa() {
        return ResponseEntity.ok(this.service.reporteTiempoSinPausa());
    }
    @GetMapping("/reporte/km")
    public ResponseEntity<List<MonopatinDTO>> reporteKm() {
        return ResponseEntity.ok(this.service.reporteKm());
    }
    @GetMapping("/cercanos")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinesCercanos(CoordenadaDTO c) {
        return ResponseEntity.ok(this.service.monopatinesCercanos(c.getX(), c.getY()));
    }
    @GetMapping("/paradaDeMonopatin/{id}")
    public ResponseEntity<ParadaDTO> getP(@PathVariable Integer id) {
        return ResponseEntity.ok(this.service.getP(id));
    }

    @PostMapping("")
    public ResponseEntity<String> nuevo(@RequestBody Monopatin m) {
        try {
            service.nuevoMonopatin(m);
            return new ResponseEntity<>("Monopatin agregado.", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("No se pudo agregar un nuevo monopatin.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id){
        try {
            service.borrarMonopatin(id);
            return new ResponseEntity<>("Monopatin eliminado.", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("No se pudo eliminar el monopatin con id: "+id, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/mantenimiento/agregar/{id}")
    public ResponseEntity<MonopatinDTO> agregarMantenimiento(@PathVariable Integer id) {
        return ResponseEntity.ok(service.agregarMantenimiento(id));
    }
    @PutMapping("/mantenimiento/quitar/{id}")
    public ResponseEntity<MonopatinDTO> quitarMantenimiento(@PathVariable Integer id) {
        return ResponseEntity.ok(service.quitarMantenimiento(id));
    }
}
