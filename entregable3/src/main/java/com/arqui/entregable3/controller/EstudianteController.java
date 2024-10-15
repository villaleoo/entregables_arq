package com.arqui.entregable3.controller;

import io.swagger.annotations.Api;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.arqui.entregable3.model.DTO.EstudianteDTO;
import com.arqui.entregable3.model.Entities.Estudiante;
import com.arqui.entregable3.service.EstudianteService;
import com.arqui.entregable3.utils.enums.Genero;

@RestController
@RequestMapping("estudiantes")
@Api(value = "EstudianteController", description = "REST API Estudiante description")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }
    
    
       // Recuperar estudiante por su numero de libreta
       @GetMapping("libreta/{num_libreta}")
       public Iterable<EstudianteDTO> getPersonsByNumLibreta(@PathVariable String num_libreta) {
           String num_libretaMin = num_libreta.toLowerCase();
           return service.getPersonsByNumLibreta(num_libretaMin);
       }
       @GetMapping("/genero/{genero}")
       public Iterable<EstudianteDTO> getPersonsByGenero(@PathVariable String genero) {
           Genero generoEnum = Genero.valueOf(genero.toUpperCase());
           return service.getPersonsByGenero(generoEnum);
       }
    
    // Dar de alta a un estudiante
    @PostMapping("/add")
    public Estudiante newEstudiante(@RequestBody Estudiante p) {
        return service.newEstudiante(p);
    }

    // Recuperar todos los estudiantes
    @GetMapping
    public Iterable<Estudiante> getEstudiantes() {
        return service.getEstudiantes();
    }
    
    
    
 

    //Recuperar estudiantes por su genero
        // Recuperar estudiante por id
    @GetMapping("{id}")
    public Optional<Estudiante> getEstudianteById(@PathVariable int id) {
        return service.getEstudianteById(id);
    }



    //Recuperar estudiantes por carrera y ciudad de residencia
    @GetMapping("carrera/{carrera}/ciudad{ciudad}")
    public Iterable<EstudianteDTO> getPersonsByCarreraAndCiudad(@PathVariable String carrera,@PathVariable String ciudad) {
        return service.getPersonsByCarreraAndCiudad(carrera, ciudad);
    }
}
