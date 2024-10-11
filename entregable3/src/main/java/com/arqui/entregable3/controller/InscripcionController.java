package com.arqui.entregable3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arqui.entregable3.model.Entities.Inscripcion;
import com.arqui.entregable3.repository.InscripcionRepository;
import com.arqui.entregable3.service.InscripcionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("inscripciones")
public class InscripcionController {
    @Autowired
    private final InscripcionService service;

    public InscripcionController(InscripcionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Iterable<Inscripcion> traerInscripciones() {
        return service.getInscripciones();
    }

    @PostMapping("/inscripciones/add")
    Inscripcion matricularEstudiante(@RequestBody Inscripcion i) {
        return service.matricularEstudiante(i);
    }
}