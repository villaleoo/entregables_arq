package Monopatin_servicio.Controller;

import Monopatin_servicio.Dto.MonopatinDTO;
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
    @GetMapping("/")
    public ResponseEntity<List<MonopatinDTO>> listarTodos() {
        return ResponseEntity.ok(this.service.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MonopatinDTO> listarUno(@PathVariable Integer id) {
        return ResponseEntity.ok(this.service.listarUno(id));
    }
    @PostMapping("/")
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
}
