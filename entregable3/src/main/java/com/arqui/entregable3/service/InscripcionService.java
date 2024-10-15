package com.arqui.entregable3.service;

import org.springframework.stereotype.Service;

import com.arqui.entregable3.model.Entities.Inscripcion;
import com.arqui.entregable3.repository.InscripcionRepository;

@Service
public class InscripcionService {
    private final InscripcionRepository repo;

    public InscripcionService(InscripcionRepository repo) {
        this.repo = repo;
    }

     
    public Iterable<Inscripcion> getInscripciones() {
        return repo.getInscripciones();
    }

    public Inscripcion matricularEstudiante(Inscripcion i) {
        return repo.save(i);
    }
 
    
}
