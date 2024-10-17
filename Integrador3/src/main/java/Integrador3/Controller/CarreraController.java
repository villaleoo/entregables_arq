package Integrador3.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Integrador3.DTO.CarreraDTO;
import Integrador3.DTO.CarreraInscriptosDTO;
import Integrador3.Service.CarreraService;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody CarreraDTO carrera) {
        try {
            carreraService.add(carrera);
            return new ResponseEntity<>("Carrera creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error, ingresaste mal el nombre de una columna " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> getById(@PathVariable Long id) {
        CarreraDTO carrera = carreraService.getById(id);
        if (carrera != null)
            return new ResponseEntity<>(carrera, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<Page<CarreraDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size,
                                                   @RequestParam(defaultValue = "nombreCarrera, asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return new ResponseEntity<>(carreraService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/inscriptos")
    public ResponseEntity<Page<CarreraInscriptosDTO>> getCarrerasConEstudiantesInscriptos(@RequestParam(defaultValue = "0") Integer page,
                                                                                          @RequestParam(defaultValue = "10") Integer size,
                                                                                          @RequestParam(defaultValue = "c.nombreCarrera, asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return new ResponseEntity<>(carreraService.getCarrerasConEstudiantesInscriptos(pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            carreraService.delete(id);
            return new ResponseEntity<>("Carrera eliminada exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La carrera con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreraDTO> update(@PathVariable Long id, @RequestBody CarreraDTO carrera) {
        try {
            carreraService.update(id, carrera);
            return new ResponseEntity<>(carrera, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
