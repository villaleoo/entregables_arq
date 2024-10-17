package Integrador3.Controller;


import Integrador3.DTO.EstudianteSearchDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Integrador3.DTO.EstudianteDTO;
import Integrador3.Service.EstudianteService;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody EstudianteDTO estudiante) {
        try {
            estudianteService.add(estudiante);
            return new ResponseEntity<>("Estudiante creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error." + e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getById(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.getById(id);
        if (estudiante != null)
            return new ResponseEntity<>(estudiante, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<Page<EstudianteDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "10") Integer size,
                                                      @RequestParam(defaultValue = "edad, asc") String[] sort) {


        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        return new ResponseEntity<>(estudianteService.getAll(pageable), HttpStatus.OK);
    }

    /*@GetMapping("/orderby")
    public ResponseEntity<Page<EstudianteDTO>> getEstudiantesOrderedBy(@RequestParam(defaultValue = "0") Integer page,
                                                                       @RequestParam(defaultValue = "10") Integer size,
                                                                       @RequestParam(defaultValue = "nombre, asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        return new ResponseEntity<>(estudianteService.getEstudiantesOrderedBy(pageable), HttpStatus.OK);
    }*/

    @GetMapping("/search")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesByAttribute(EstudianteSearchDTO request) {
        try {
            return new ResponseEntity<>(estudianteService.getEstudiantesByAttribute(request), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            estudianteService.delete(id);
            return new ResponseEntity<>("Estudiante eliminado exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("El estudiante con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@PathVariable Long id, @RequestBody EstudianteDTO estudiante) {
        try {
            estudianteService.update(id, estudiante);
            return new ResponseEntity<>(estudiante, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
