package com.arqui.entregable3.controller;

import com.arqui.entregable3.model.DTO.CarreraDTO;
import com.arqui.entregable3.model.DTO.ReporteDTO;
import com.arqui.entregable3.model.Entities.Carrera;
import com.arqui.entregable3.service.CarreraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("carreras")
public class CarreraController {

    @Autowired
    private final CarreraService service;

    public CarreraController(CarreraService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public Carrera addCarrera(@RequestBody Carrera c) {
    return service.addCarrera(c);
    }

    @GetMapping
    public Iterable<CarreraDTO> getCarreras() {
        return service.getCarreras();
    }

    @GetMapping("reporte")
    public Iterable<ReporteDTO> getReporte(){
        return service.getReporte();
    }
}
