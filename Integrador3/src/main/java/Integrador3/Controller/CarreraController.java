package Integrador3.Controller;

import jakarta.persistence.EntityNotFoundException;
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
        try {
            carreraService.addCarrera(carrera);
            return new ResponseEntity<>("Carrera creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error, ingresaste mal el nombre de una columna " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
        try {
            carreraService.deleteCarrera(id);
            return new ResponseEntity<>("Carrera eliminada exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La carrera con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CarreraDTO> updateCarrera(@PathVariable Long id, @RequestBody CarreraDTO carrera) {
        try {
            carreraService.updateCarrera(id, carrera);
            return new ResponseEntity<>(carrera, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
