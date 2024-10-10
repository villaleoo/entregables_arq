package org.example.integrador_3.servicio;

import org.example.integrador_3.persistencia.DTO.CarreraDto;
import org.example.integrador_3.persistencia.DTO.EstudianteDto;
import org.example.integrador_3.persistencia.DTO.ReporteDto;
import org.example.integrador_3.persistencia.modelo.Carrera;
import org.example.integrador_3.persistencia.modelo.Estudiante;
import org.example.integrador_3.repositorio.CarreraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraServicio {
    @Qualifier("carreraRepo")
    @Autowired
    private final CarreraRepo r;
    public CarreraServicio(@Qualifier("carreraRepo")CarreraRepo repo) {
        this.r = repo;
    }

    public Iterable<Carrera> traerCarreras() { return r.findAll(); }

    public Optional<Carrera> traerUnaCarrera(Integer id) { return r.findById(id);}

    public Iterable<CarreraDto> traerCarrerasPorCantEstudiantes() { return r.traerCarrerasConEstudiantes(); }

    public Iterable<ReporteDto> traerReporte() {
        return r.traerReporte();
    }

    public Carrera nuevaCarrera(Carrera c) { return r.save(c); }
}
