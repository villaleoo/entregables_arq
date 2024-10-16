package org.example.entregable3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Inscripcion {

    @EmbeddedId
    private InscripcionId id_inscripcion;

    @ManyToOne
    @MapsId("estudianteId") /*mapea con los id de incripcionId*/
    @JoinColumn(name="id_persona")
    private Persona estudiante;

    @ManyToOne
    @MapsId("carreraId") /*mapea con los id de incripcionId*/
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column (nullable = false)
    private LocalDate fecha_inscripcion;

    private LocalDate fecha_egreso;


    public Inscripcion(Persona persona, Carrera carrera) {
        this.estudiante = persona;
        this.carrera = carrera;
        this.fecha_egreso = null;
        this.fecha_inscripcion= LocalDate.now();
        this.id_inscripcion= new InscripcionId(persona.getId(), carrera.getId_carrera());
    }

    public Inscripcion (Persona persona, Carrera carrera, LocalDate inscripcion, LocalDate egreso){   ///aca se podria controlar que la fecha de inscrip sea menor a la de egreso
        this(persona,carrera);
        this.fecha_inscripcion=inscripcion;
        this.fecha_egreso=egreso;
    }

    public Inscripcion() {

    }
}