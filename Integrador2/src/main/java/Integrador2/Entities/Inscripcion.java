package Integrador2.Entities;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Inscripcion {
    @EmbeddedId
    private InscripcionID id;

    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera")
    private Carrera carrera;

    @ManyToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    private Estudiante estudiante;

    @Column()
    private Long antiguedadEnCarrera;

    @Column()
    private boolean graduado;

    @Column
    private LocalDate fechaInscripcion;

    public Inscripcion() {
    }

    public Inscripcion(Carrera carrera, Estudiante estudiante, LocalDate fechaInscripcion) {
        this.id = new InscripcionID(carrera.getIdCarrera(), estudiante.getIdEstudiante());
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.antiguedadEnCarrera = ChronoUnit.YEARS.between(fechaInscripcion, LocalDate.now());
        this.graduado = false;
        this.fechaInscripcion = fechaInscripcion;
    }

    public InscripcionID getId() {
        return id;
    }

    public Long getAntiguedadEnCarrera() {
        return antiguedadEnCarrera;
    }

    public void setAntiguedadEnCarrera(Long antiguedadEnCarrera) {
        this.antiguedadEnCarrera = antiguedadEnCarrera;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", carrera=" + carrera +
                ", estudiante=" + estudiante +
                ", antiguedadEnCarrera=" + antiguedadEnCarrera +
                ", graduado=" + graduado +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}
