package com.arqui.entregable3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arqui.entregable3.model.Entities.Inscripcion;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    
    @Query("SELECT i FROM Inscripcion i")
    List<Inscripcion> getInscripciones();

    
}
