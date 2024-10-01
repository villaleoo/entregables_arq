package Entities;

import utils.enums.Genero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante extends Persona {
    @Column(name="num_libreta", unique = true, nullable = false)
    private String num_libreta;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    private List<Inscripcion> carreras_inscriptas;

    // Constructor sin argumentos (no-arg constructor)
    public Estudiante() {
        super(); // Llama al constructor de la clase base Persona
        this.carreras_inscriptas = new ArrayList<>();
    }

    public Estudiante(String nombre, String apellido, LocalDate fecha_nacimiento, Genero genero, int dni, String ciudad_residencia, String num_libreta) {
        super(nombre, apellido, fecha_nacimiento, genero, dni, ciudad_residencia);
        this.num_libreta = num_libreta.toLowerCase().trim();
        this.carreras_inscriptas = new ArrayList<>();
    }

    public String getNum_libreta() {
        return num_libreta;
    }

    public void setNum_libreta(String num_libreta) {
        this.num_libreta = num_libreta;
    }

    public void addInscripcion(Inscripcion i) {
        this.carreras_inscriptas.add(i);
    }

    private ArrayList<Integer> getIdCarreras() {
        ArrayList<Integer> c = new ArrayList<>();
        for (Inscripcion i : carreras_inscriptas) {
            c.add(i.getIdCarrera());
        }
        return c;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id_persona=" + id_persona +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", genero=" + genero +
                ", dni=" + dni +
                ", residencia='" + ciudad_residencia + '\'' +
                ", libreta='" + num_libreta + '\'' +
                '}';
    }
}
