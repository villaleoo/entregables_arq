package com.arqui.entregable3.service;

import java.util.Optional;

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

    public Iterable<EstudianteDTO> getEstudiantesByNumLibreta(String num_libreta) {
        return repo.findAllByNumLibreta(num_libreta);
    }

    public Iterable<EstudianteDTO> getEstudiantesByGenero(Genero genero) {
        return repo.findAllByGenero(genero);
    }

    public Estudiante newEstudiante(Estudiante p) {
        return repo.save(p);
    }

    public Iterable<Estudiante> getEstudiantes() {
        return repo.findAll();
    }

    public Optional<Estudiante> getEstudianteById(int id) {
        return repo.findById(id);
    }

    public Iterable<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad) {
        return repo.findAllByCarreraAndCiudad(carrera, ciudad);

    }
}
