package Entities;
import utils.enums.Facultad;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_carrera;
    @Column(nullable = false, length = 180)
    private String nombre_carrera;
    @Enumerated(EnumType.STRING)
    private Facultad facultad;
    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;

    public Carrera(String nombre_carrera, Facultad facultad) {
        this.nombre_carrera = nombre_carrera;
        this.facultad = facultad;
        this.inscripciones = new ArrayList<>();
    }

    public Carrera() {
        super();
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id_carrera=" + id_carrera +
                ", nombre_carrera='" + nombre_carrera + '\'' +
                ", facultad=" + facultad +
                ", inscripciones=" + inscripciones +
                '}';
    }
}
