package Integrador3.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Inscripcion {
    @EmbeddedId
    private InscripcionID id;

    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera")
    private Carrera carrera;

    @ManyToOne
    @MapsId("documento")
    @JoinColumn(name = "documento", referencedColumnName = "documento")
    private Estudiante estudiante;

    @Transient
    private Long antiguedadEnCarrera;

    @Column
    private LocalDate fechaInscripcion;

    @Column
    private LocalDate fechaGraduacion;

    public Inscripcion(Carrera carrera, Estudiante estudiante) {
        this.id = new InscripcionID(carrera.getIdCarrera(), (long) estudiante.getDocumento());
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.fechaInscripcion = LocalDate.now();
        this.fechaGraduacion = null;
        this.setAntiguedadEnCarrera();
    }

    public Inscripcion(Carrera carrera, Estudiante estudiante, LocalDate fechaInscripcion, LocalDate fechaGraduacion) {
        this.id = new InscripcionID(carrera.getIdCarrera(), (long) estudiante.getDocumento());
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = fechaGraduacion;
        this.setAntiguedadEnCarrera();
    }

    public Inscripcion(Long idCarrrera, Long dniEstudiante, LocalDate fechaInscripcion, LocalDate fechaGraduacion) {
        this.id = new InscripcionID(idCarrrera, dniEstudiante);
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = fechaGraduacion;
    }

    private void setAntiguedadEnCarrera() {
        if (fechaGraduacion != null)
            this.antiguedadEnCarrera = ChronoUnit.YEARS.between(fechaInscripcion, fechaGraduacion);
        this.antiguedadEnCarrera = ChronoUnit.YEARS.between(fechaInscripcion, LocalDate.now());
    }


    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", carrera=" + carrera +
                ", estudiante=" + estudiante +
                ", antiguedadEnCarrera=" + antiguedadEnCarrera +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}
