package com.arqui.entregable3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arqui.entregable3.model.Entities.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    /* 
    @Query("SELECT i FROM Inscripcion i")
    List<Inscripcion> getInscripciones();
*/
    
}
