package Integrador3.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Integrador3.DTO.EstudianteCarreraDTO;
import Integrador3.DTO.EstudianteDTO;
import Integrador3.Service.EstudianteService;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("/create")
    public ResponseEntity<String> createEstudiante(@RequestBody EstudianteDTO estudiante) {
        estudianteService.addEstudiante(estudiante);
        return new ResponseEntity<>("Estudiante creado exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.getEstudianteById(id);
        if (estudiante != null)
            return new ResponseEntity<>(estudiante, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        return new ResponseEntity<>(estudianteService.getAllEstudiantes(), HttpStatus.OK);
    }

    @GetMapping("/orderby/{criterio}")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesOrderedBy(@PathVariable String criterio) {
        return new ResponseEntity<>(estudianteService.getEstudiantesOrderedBy(criterio), HttpStatus.OK);
    }

    @GetMapping("/nroLibreta/{nroLibreta}")
    public ResponseEntity<EstudianteDTO> getEstudianteByNroLibreta(@PathVariable int nroLibreta) {
        return new ResponseEntity<>(estudianteService.getEstudianteByNroLibreta(nroLibreta), HttpStatus.OK);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesByGenero(@PathVariable String genero) {
        return new ResponseEntity<>(estudianteService.getEstudiantesByGenero(genero), HttpStatus.OK);
    }

    @GetMapping("/carrera/{carrera}/ciudad/{ciudad}")
    public ResponseEntity<List<EstudianteCarreraDTO>> getEstudiantesByCarreraAndCiudad(@PathVariable String carrera, @PathVariable String ciudad) {
        return new ResponseEntity<>(estudianteService.getEstudiantesByCarreraAndCiudad(carrera, ciudad), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return new ResponseEntity<>("Estudiante eliminado exitosamente", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EstudianteDTO> updateEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudiante) {
        estudianteService.updateEstudiante(id, estudiante);
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }
}
