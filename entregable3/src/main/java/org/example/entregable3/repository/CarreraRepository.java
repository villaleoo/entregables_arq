package org.example.entregable3.repository;

import org.example.entregable3.DTO.CarreraInscriptosDTO;
import org.example.entregable3.DTO.EgresoDTO;
import org.example.entregable3.DTO.InscripcionDTO;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT new org.example.entregable3.DTO.CarreraInscriptosDTO (c.titulo, COUNT(i.carrera.id_carrera)) " +
            "FROM Inscripcion i " +
            "JOIN i.carrera c " +
            "GROUP BY c.titulo " +
            "ORDER BY COUNT(i.carrera.id_carrera) DESC")
    List<CarreraInscriptosDTO> getAllEnrolled();

    @Query("SELECT c FROM Carrera c ORDER BY c.titulo ASC")
    public List<Carrera> findAllByTitleAsc();

    @Query("SELECT new org.example.entregable3.DTO.InscripcionDTO(i.id_inscripcion.estudianteId,i.id_inscripcion.carreraId, i.fecha_inscripcion, i.fecha_egreso) " +
            "FROM Inscripcion i WHERE i.carrera = :c " +
            "ORDER BY i.fecha_inscripcion DESC")
    public List<InscripcionDTO> findInscripciones(Carrera c);

    @Query("SELECT new org.example.entregable3.DTO.EgresoDTO(i.id_inscripcion.estudianteId, i.fecha_egreso) " +
            "FROM Inscripcion i WHERE i.carrera = :c AND i.fecha_egreso IS NOT NULL " +
            "ORDER BY i.fecha_egreso DESC")
    public List<EgresoDTO> findEgresos (Carrera c);
}
