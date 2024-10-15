package com.arqui.entregable3.model.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@Entity
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_inscripcion;

    @ManyToOne
    @JoinColumn(name="id_persona")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column (nullable = true)
    private LocalDate fecha_inscripcion;
    @Column (nullable = true)
    private LocalDate fecha_egreso;


    public Inscripcion(Estudiante persona, Carrera carrera) {
        this.estudiante = persona;
        this.carrera = carrera;
        this.fecha_egreso = null;
        this.fecha_inscripcion= LocalDate.now();
    }

    public Inscripcion (Estudiante persona, Carrera carrera, LocalDate inscripcion, LocalDate egreso){   ///aca se podria controlar que la fecha de inscrip sea menor a la de egreso
        this(persona,carrera);
        this.fecha_inscripcion=inscripcion;
        this.fecha_egreso=egreso;
    }


    public Long getIdEstudiante() {
        return estudiante.getId();
    }

    public Long getIdCarrera() {
        return carrera.getId_carrera();
    }

    public LocalDate getFechaInscripcion() {
        return fecha_inscripcion;
    }

    public void setFechaEgreso(LocalDate fecha) {
        this.fecha_egreso=fecha;
    }

    public void setFechaEgresoNow(){
        this.fecha_egreso= LocalDate.now();
    }

    public void setFechaInscripcion(LocalDate fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public void imprimirAntiguedad(){
        LocalDate endDate = LocalDate.now();
        Period difference = Period.between(this.fecha_inscripcion, endDate);

        System.out.println("Antigüedad: " + difference.getYears() + " años, " +
                difference.getMonths() + " meses, y " +
                difference.getDays() + " días.");
    }

    public boolean isGraduo(){
        return this.fecha_egreso != null;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id_inscripcion=" + id_inscripcion +
                ", estudianteId=" + (estudiante != null ? estudiante.getId() : "null") +
                ", carreraId=" + (carrera != null ? carrera.getId_carrera() : "null") +
                ", fecha_inscripcion=" + fecha_inscripcion +
                ", fecha_egreso=" + fecha_egreso +
                '}';
    }
}