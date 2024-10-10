package org.example.integrador_3.servicio;

import org.example.integrador_3.persistencia.DTO.InscripcionDto;
import org.example.integrador_3.persistencia.modelo.Inscripcion;
import org.example.integrador_3.repositorio.InscripcionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class InscripcionServicio {
    @Qualifier("inscripcionRepo")
    @Autowired
    private final InscripcionRepo r;
    public InscripcionServicio(@Qualifier("inscripcionRepo")InscripcionRepo repo) {
        this.r = repo;
    }

    public Iterable<Inscripcion> traerInscripciones() { return r.findAll(); }

    public InscripcionDto traerUnaInscripcion(Integer estudiante_id, Integer carrera_id) {
        return r.traerUnaInscripcion(estudiante_id, carrera_id);
    }

    public Inscripcion matricularEstudiante(Inscripcion i) { return r.save(i); }
    
}
