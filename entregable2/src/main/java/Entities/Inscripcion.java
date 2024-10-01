package Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Inscripcion {
    @EmbeddedId
    private InscripcionId id_inscripcion;

    @ManyToOne
    @MapsId("estudianteId")
    @JoinColumn(name="id_persona")

    private Persona estudiante;

    @ManyToOne
    @MapsId("carreraId")
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column(nullable = false)
    private LocalDate fecha_inscripcion;
    private LocalDate fecha_egreso;

    public Inscripcion() {
        super();
    }
    public Inscripcion(Persona estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fecha_egreso = null;
        this.fecha_inscripcion = LocalDate.now();
        this.id_inscripcion = new InscripcionId(estudiante.getId(), carrera.getId_carrera());
    }

    public InscripcionId getId_inscripcion() {
        return id_inscripcion;
    }

    public int getIdEstudiante() {
        return estudiante.getId();
    }

    public Integer getIdCarrera() {
        return carrera.getId_carrera();
    }

    public LocalDate getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public LocalDate getFecha_egreso() {
        return fecha_egreso;
    }

    public void setEstudiante(Persona estudiante) {
        this.estudiante = estudiante;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public void setFecha_inscripcion(LocalDate fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public void setFecha_egreso(LocalDate fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
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

