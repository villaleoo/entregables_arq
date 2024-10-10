package org.example.integrador_3.persistencia.DTO;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import org.example.integrador_3.persistencia.modelo.Carrera;
import org.example.integrador_3.persistencia.modelo.Estudiante;
import org.example.integrador_3.persistencia.modelo.EstudianteCarreraId;

public class InscripcionDto {

    private EstudianteCarreraId id;
    private Estudiante estudiante;
    private Carrera carrera;
    private Integer anio_inicio;
    private Integer anio_Graduacion;

    public InscripcionDto(){}

    public InscripcionDto(Estudiante estudiante, Carrera carrera,
                       Integer anio_inicio, Integer anio_Graduacion) {
        this.id = new EstudianteCarreraId(estudiante.getId(), carrera.getId());
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.anio_inicio = anio_inicio;
        this.anio_Graduacion = anio_Graduacion;
    }
}
