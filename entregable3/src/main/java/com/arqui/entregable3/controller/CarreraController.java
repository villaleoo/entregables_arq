package com.arqui.entregable3.controller;

import com.arqui.entregable3.model.Entities.Carrera;
import com.arqui.entregable3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carreras")
public class CarreraController {

    @Autowired
    private final CarreraRepository repository;

    public CarreraController(CarreraRepository repository) {
        this.repository = repository;
    }

    // Recuperar carreras ordenadas por cantidad de inscriptos
    @GetMapping("/orderedByInscriptos")
    public List<Carrera> getCarrerasOrderedByInscriptos() {
        return repository.findAllOrderedByInscriptos();
    }
}
