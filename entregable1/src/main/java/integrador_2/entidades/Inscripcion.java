package integrador_2.entidades;

import javax.persistence.*;

@Entity
@Table(name = "inscripcion")
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

    public EstudianteCarreraId getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Integer getAnio_inicio() {
        return anio_inicio;
    }

    public void setAnio_inicio(Integer anio_inicio) {
        this.anio_inicio = anio_inicio;
    }

    public Integer getAnio_Graduacion() {
        return anio_Graduacion;
    }

    public void setAnio_Graduacion(Integer anio_Graduacion) {
        this.anio_Graduacion = anio_Graduacion;
    }
}
