package com.arqui.entregable3.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.arqui.entregable3.model.DTO.EstudianteDTO;
import com.arqui.entregable3.model.Entities.Estudiante;
import com.arqui.entregable3.repository.EstudianteRepository;
import com.arqui.entregable3.utils.enums.Genero;


@Service
public class EstudianteService {
    private final EstudianteRepository repo;

    public EstudianteService(EstudianteRepository repo) {
        this.repo = repo;
    }

    public Iterable<Estudiante> getEstudiantes() {
        return repo.findAll();
    }

    public Estudiante newEstudiante(Estudiante p) {
        return repo.save(p);
    }

    public Iterable<EstudianteDTO> getPersonsByLibreta(String num_libreta) {
        return repo.findAllByLibreta(num_libreta);
    }

    public Iterable<EstudianteDTO> getPersonsByGenero(Genero genero) {
        return repo.findAllByGenero(genero);
    }
}
