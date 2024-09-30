package model;

import utils.enums.Facultad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id_carrera;
    @Column(nullable = false, length = 180)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Facultad facultad;
    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;

    public Carrera ( String titulo, Facultad facultad){
        this.titulo=titulo.toLowerCase().trim();
        this.facultad=facultad;
        this.inscripciones=new ArrayList<>();
    }

    public Carrera() {
        super();
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo.toLowerCase().trim();
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }




    @Override
    public String toString() {
        return "Carrera{" +
                "titulo='" + titulo + '\'' +
                ", facultad=" + facultad +
                ", cant_inscriptos=" + inscripciones.size() +
                '}';
    }
}
