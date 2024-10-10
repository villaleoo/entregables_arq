package org.example.integrador_3.servicio;

import org.example.integrador_3.persistencia.DTO.EstudianteDto;
import org.example.integrador_3.persistencia.modelo.Estudiante;
import org.example.integrador_3.repositorio.EstudianteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio {
    @Qualifier("estudianteRepo")
    @Autowired
    private final EstudianteRepo r;
    public EstudianteServicio(@Qualifier("estudianteRepo") EstudianteRepo repo) {
        this.r = repo;
    }

    public Iterable<Estudiante> traerEstudiantes() { return r.findAll(); }

    public Optional<Estudiante> traerUnEstudiante(Integer id) { return r.findById(id);}

    public EstudianteDto traerEstudiantePorNroLibreta(Integer nro_libreta) {
        return r.traerEstudiantePorNroLibreta(nro_libreta);
    }

    public List<EstudianteDto> traerEstudiantesPorGenero(String genero) {
        return r.traerEstudiantesPorGenero(genero);
    }

    public List<EstudianteDto> traerEstudiantesPorCarreraCiudad(String carrera, String ciudad) {
        return r.filtrarCarreraCiudad(carrera, ciudad);
    }

    public Estudiante nuevoEstudiante(Estudiante e) { return r.save(e); }

}
