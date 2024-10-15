package com.arqui.entregable3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arqui.entregable3.model.DTO.CarreraDTO;
import com.arqui.entregable3.model.DTO.ReporteDTO;
import com.arqui.entregable3.model.Entities.Carrera;
import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT new com.arqui.entregable3.model.DTO.CarreraDTO(c.titulo, COUNT(i.carrera.id_carrera)) FROM Carrera c JOIN Inscripcion i ON c.id_carrera = i.carrera.id_carrera GROUP BY c.titulo HAVING COUNT(i.carrera.id_carrera) > 0  ORDER BY COUNT(i.carrera.id_carrera) DESC")
    List<CarreraDTO> getCarreras();

    @Query("SELECT new com.arqui.entregable3.model.DTO.ReporteDTO(c.id_carrera,c.titulo, COUNT(i.estudiante.id_persona),(SELECT COUNT(i2.estudiante.id_persona) FROM Inscripcion i2 WHERE i2.carrera.id_carrera = c.id_carrera AND i2.fecha_egreso IS NOT NULL)) FROM Carrera c JOIN Inscripcion i ON c.id_carrera = i.carrera.id_carrera GROUP BY c.id_carrera, c.titulo ORDER BY c.titulo ASC ")
    Iterable<ReporteDTO> getReporte();

}
