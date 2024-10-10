package org.example.integrador_3.persistencia.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Inscripcion {
    @Id
    private EstudianteCarreraId id;
    @ManyToOne
    @MapsId("estudiante_id")
    @JoinColumn(name="estudiante_id")
    private Estudiante estudiante;
    @ManyToOne
    @MapsId("carrera_id")
    @JoinColumn(name="carrera_id")
    private Carrera carrera;
    private Integer anio_inicio;
    private Integer anio_Graduacion;

    public Inscripcion(){}

    public Inscripcion(Estudiante estudiante, Carrera carrera,
                       Integer anio_inicio, Integer anio_Graduacion) {
        this.id = new EstudianteCarreraId(estudiante.getId(), carrera.getId());
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.anio_inicio = anio_inicio;
        this.anio_Graduacion = anio_Graduacion;
    }
}
