package com.arqui.entregable3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arqui.entregable3.service.InscripcionService;

@RestController
@RequestMapping("inscripciones")
public class InscripcionController {
    @Autowired
    private final InscripcionService service;

    public InscripcionController(InscripcionService service) {
        this.service = service;
    }

    /* 
    @GetMapping
    public Iterable<Inscripcion> traerInscripciones() {
        return service.getInscripciones();
    }

    @PostMapping("/matricular")
    public Inscripcion matricularEstudiante(@RequestBody Inscripcion i) {
        return service.matricularEstudiante(i);
    }
        */
}