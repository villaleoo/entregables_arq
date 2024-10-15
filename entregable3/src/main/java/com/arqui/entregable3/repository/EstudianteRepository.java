package com.arqui.entregable3.repository;

import com.arqui.entregable3.model.DTO.EstudianteDTO;
import com.arqui.entregable3.model.Entities.Estudiante;
import com.arqui.entregable3.utils.enums.Genero;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    @Query("SELECT new com.arqui.entregable3.model.DTO.EstudianteDTO(e.id_persona, e.nombre, e.apellido, e.fecha_nacimiento, e.genero, e.dni, e.ciudad_residencia, e.num_libreta) FROM Estudiante e WHERE e.num_libreta = :num_libreta")
    List<EstudianteDTO> findAllByNumLibreta(String num_libreta);

    @Query("SELECT new com.arqui.entregable3.model.DTO.EstudianteDTO(e.id_persona, e.nombre, e.apellido, e.fecha_nacimiento, e.genero, e.dni, e.ciudad_residencia, e.num_libreta) FROM Estudiante e WHERE e.genero = :genero")
    public List<EstudianteDTO> findAllByGenero(Genero genero);

    @Query("SELECT new com.arqui.entregable3.model.DTO.EstudianteDTO(e.id_persona, e.nombre, e.apellido, e.fecha_nacimiento, e.genero, e.dni, e.ciudad_residencia, e.num_libreta) FROM Estudiante e JOIN Inscripcion i ON e.id_persona = i.estudiante.id_persona JOIN Carrera c ON i.carrera.id_carrera = c.id_carrera WHERE e.ciudad_residencia = :ciudad AND c.titulo = :carrera")
    List<EstudianteDTO> findAllByCarreraAndCiudad(String carrera, String ciudad);

    /*
     * @Query("SELECT e FROM Estudiante e JOIN Inscripcion i ON e.id_persona = i.estudianteId WHERE e.ciudad = :ciudad AND i.carreraId = (SELECT c.id_carrera FROM Carrera WHERE c.titulo = carrera) "
     * )
     * public List<Estudiante> findByCarreraAndCiudad(String carrera, String
     * ciudad);
     */
}
