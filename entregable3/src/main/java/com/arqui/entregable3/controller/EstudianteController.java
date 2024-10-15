package com.arqui.entregable3.controller;


import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.arqui.entregable3.model.DTO.EstudianteDTO;
import com.arqui.entregable3.model.Entities.Estudiante;
import com.arqui.entregable3.service.EstudianteService;
import com.arqui.entregable3.utils.enums.Genero;

@RestController
@RequestMapping("estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Estudiante> getEstudiantes() {
        return service.getEstudiantes();
    }

    @GetMapping("libreta/{num_libreta}")
    public Iterable<EstudianteDTO> getEstudiantesByNumLibreta(@PathVariable String num_libreta) {
        String num_libretaMin = num_libreta.toLowerCase();
        return service.getEstudiantesByNumLibreta(num_libretaMin);
    }

    @GetMapping("/genero/{genero}")
    public Iterable<EstudianteDTO> getEstudiantesByGenero(@PathVariable String genero) {
        Genero generoEnum = Genero.valueOf(genero.toUpperCase());
        return service.getEstudiantesByGenero(generoEnum);
    }

    @PostMapping("/add")
    public Estudiante newEstudiante(@RequestBody Estudiante p) {
        return service.newEstudiante(p);
    }

    @GetMapping("{id}")
    public Optional<Estudiante> getEstudianteById(@PathVariable int id) {
        return service.getEstudianteById(id);
    }

    @GetMapping("carrera/{carrera}/ciudad/{ciudad}")
    public Iterable<EstudianteDTO> getEstudiantesByCarreraAndCiudad(@PathVariable String carrera,
            @PathVariable String ciudad) {
        return service.getEstudiantesByCarreraAndCiudad(carrera, ciudad);
    }
}
