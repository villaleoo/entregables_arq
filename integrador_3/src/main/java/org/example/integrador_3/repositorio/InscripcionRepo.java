package org.example.integrador_3.repositorio;

import org.example.integrador_3.persistencia.DTO.InscripcionDto;
import org.example.integrador_3.persistencia.modelo.EstudianteCarreraId;
import org.example.integrador_3.persistencia.modelo.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InscripcionRepo extends JpaRepository<Inscripcion, EstudianteCarreraId> {
    @Query("select new org.example.integrador_3.persistencia.DTO.InscripcionDto(i.estudiante, i.carrera, i.anio_inicio, i.anio_Graduacion) " +
            "from Inscripcion i where i.estudiante.id=:estudiante and i.carrera.id=:carrera")
    public InscripcionDto traerUnaInscripcion(Integer estudiante, Integer carrera);
}
