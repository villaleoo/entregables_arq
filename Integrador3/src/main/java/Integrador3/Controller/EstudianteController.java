package Integrador3.Controller;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Integrador3.DTO.EstudianteCarreraDTO;
import Integrador3.DTO.EstudianteDTO;
import Integrador3.Service.EstudianteService;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("")
    public ResponseEntity<String> createEstudiante(@RequestBody EstudianteDTO estudiante) {
        try {
            estudianteService.addEstudiante(estudiante);
            return new ResponseEntity<>("Estudiante creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error." + e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.getEstudianteById(id);
        if (estudiante != null)
            return new ResponseEntity<>(estudiante, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        return new ResponseEntity<>(estudianteService.getAllEstudiantes(), HttpStatus.OK);
    }

    @GetMapping("/orderby/{criterio}")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesOrderedBy(@PathVariable String criterio) {
        return new ResponseEntity<>(estudianteService.getEstudiantesOrderedBy(criterio), HttpStatus.OK);
    }

    @GetMapping("/nroLibreta/{nroLibreta}")
    public ResponseEntity<EstudianteDTO> getEstudianteByNroLibreta(@PathVariable int nroLibreta) {
        try {
            return new ResponseEntity<>(estudianteService.getEstudianteByNroLibreta(nroLibreta), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesByGenero(@PathVariable String genero) {
        try {
            return new ResponseEntity<>(estudianteService.getEstudiantesByGenero(genero), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/carrera/{carrera}/ciudad/{ciudad}")
    public ResponseEntity<List<EstudianteCarreraDTO>> getEstudiantesByCarreraAndCiudad(@PathVariable String carrera, @PathVariable String ciudad) {
        return new ResponseEntity<>(estudianteService.getEstudiantesByCarreraAndCiudad(carrera, ciudad), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEstudiante(@PathVariable Long id) {
        try {
            estudianteService.deleteEstudiante(id);
            return new ResponseEntity<>("Estudiante eliminado exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("El estudiante con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> updateEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudiante) {
        try {
            estudianteService.updateEstudiante(id, estudiante);
            return new ResponseEntity<>(estudiante, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
