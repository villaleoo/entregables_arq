package com.arqui.entregable3.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.arqui.entregable3.model.DTO.EstudianteDTO;
import com.arqui.entregable3.model.Entities.Estudiante;
import com.arqui.entregable3.service.EstudianteService;
import com.arqui.entregable3.utils.enums.Genero;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("estudiantes")
@Api(value = "EstudianteController", description = "REST API Estudiante description")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Iterable<Estudiante> getEstudiantes() {
        return service.getEstudiantes();
    }



    @PostMapping("/add")
    public Estudiante newEstudiante(@RequestBody Estudiante p) {
        return service.newEstudiante(p);
    }

    @GetMapping("libreta/{num_libreta}")
    public Iterable<EstudianteDTO> getPersonsByLibreta(@PathVariable String num_libreta) {
        String num_libretaMin = num_libreta.toLowerCase();
        return service.getPersonsByLibreta(num_libretaMin);
    }

    @GetMapping("/genero/{genero}")
    public Iterable<EstudianteDTO> getPersonsByGenero(@PathVariable String genero) {
        Genero generoEnum = Genero.valueOf(genero.toUpperCase());
        return service.getPersonsByGenero(generoEnum);
    }

}
