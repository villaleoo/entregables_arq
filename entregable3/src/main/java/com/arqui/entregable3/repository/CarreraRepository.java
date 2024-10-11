package com.arqui.entregable3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arqui.entregable3.model.Entities.Carrera;


public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT c FROM Carrera c LEFT JOIN c.inscripciones i GROUP BY c.id_carrera ORDER BY COUNT(i) DESC")
    List<Carrera> findAllOrderedByInscriptos();
}
