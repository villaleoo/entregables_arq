package Integrador3.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @MapsId("idEstudiante")
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    private Estudiante estudiante;

    @Transient
    private Long antiguedadEnCarrera;

    @Column
    private LocalDate fechaInscripcion;

    @Column
    private LocalDate fechaGraduacion;

    public Inscripcion(Carrera carrera, Estudiante estudiante) {
        this.id = new InscripcionID(carrera.getIdCarrera(), estudiante.getIdEstudiante());
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.fechaInscripcion = LocalDate.now();
        this.fechaGraduacion = null;
        this.setAntiguedadEnCarrera();
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
