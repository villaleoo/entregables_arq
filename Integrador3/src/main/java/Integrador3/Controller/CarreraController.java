package Integrador3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Integrador3.DTO.CarreraDTO;
import Integrador3.DTO.CarreraInscriptosDTO;
import Integrador3.Service.CarreraService;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @PostMapping("/create")
    public ResponseEntity<String> createCarrera(@RequestBody CarreraDTO carrera) {
        carreraService.addCarrera(carrera);
        return new ResponseEntity<>("Carrera creada exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> getCarreraById(@PathVariable Long id) {
        CarreraDTO carrera = carreraService.getCarreraById(id);
        if (carrera != null)
            return new ResponseEntity<>(carrera, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarreraDTO>> getAllCarreras() {
        return new ResponseEntity<>(carreraService.getAllCarreras(), HttpStatus.OK);
    }

    @GetMapping("/inscriptos")
    public ResponseEntity<List<CarreraInscriptosDTO>> getCarrerasConEstudiantesInscriptos() {
        return new ResponseEntity<>(carreraService.getCarrerasConEstudiantesInscriptos(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCarrera(@PathVariable Long id) {
        carreraService.deleteCarrera(id);
        return new ResponseEntity<>("Carrera eliminada exitosamente", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CarreraDTO> updateCarrera(@PathVariable Long id, @RequestBody CarreraDTO carrera) {
        carreraService.updateCarrera(id, carrera);
        return new ResponseEntity<>(carrera, HttpStatus.OK);
    }
}
