package org.example.entregable3.repository;

import org.example.entregable3.model.Inscripcion;
import org.example.entregable3.model.InscripcionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion , InscripcionId> {
}
