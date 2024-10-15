package com.arqui.entregable3.service;

import org.springframework.stereotype.Service;

import com.arqui.entregable3.model.DTO.CarreraDTO;
import com.arqui.entregable3.model.DTO.ReporteDTO;
import com.arqui.entregable3.model.Entities.Carrera;
import com.arqui.entregable3.repository.CarreraRepository;

@Service
public class CarreraService {
    private final CarreraRepository repo;

    public CarreraService(CarreraRepository repo) {
        this.repo = repo;
    }

    public Carrera addCarrera(Carrera c) {
        return repo.save(c);
    }

    public Iterable<CarreraDTO> getCarreras() {
        return repo.getCarreras();
    }

    public Iterable<ReporteDTO> getReporte() {
        return repo.getReporte();
    }

}
