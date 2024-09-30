package model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
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

    public Inscripcion() {
        super();
    }

    public InscripcionId getId_inscripcion() {
        return id_inscripcion;
    }

    public int getIdEstudiante() {
        return estudiante.getId();
    }

    public int getIdCarrera() {
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

    public void setFecha_inscripcion(LocalDate fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
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
