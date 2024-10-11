package com.arqui.entregable3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arqui.entregable3.model.Entities.Inscripcion;
import com.arqui.entregable3.model.Entities.InscripcionId;

public interface InscripcionRepository extends JpaRepository<Inscripcion, InscripcionId> {

    @Query("SELECT i FROM Inscripcion i")
    Iterable<Inscripcion> getInscripciones();

    
}
