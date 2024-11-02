package Estacion_servicio.Controller;

import Estacion_servicio.Dto.ParadaDTO;
import Estacion_servicio.Entity.Parada;
import Estacion_servicio.Service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estaciones")
public class ParadaController {
    @Autowired
    private ParadaService service;
    @GetMapping("/")
    public ResponseEntity<List<ParadaDTO>> listarTodos() {
        return ResponseEntity.ok(this.service.listarTodas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParadaDTO> listarUno(@PathVariable Integer id) {
        return ResponseEntity.ok(this.service.listarUna(id));
    }
    @PostMapping("/")
    public ResponseEntity<String> nuevo(@RequestBody Parada m) {
        try {
            service.registrar(m);
            return new ResponseEntity<>("Estacion agregada.", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("No se pudo agregar una nueva estacion.", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id){
        try {
            service.borrarEstacion(id);
            return new ResponseEntity<>("Estacion eliminada.", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("No se pudo eliminar la estacion con id: "+id, HttpStatus.BAD_REQUEST);
        }
    }
}
