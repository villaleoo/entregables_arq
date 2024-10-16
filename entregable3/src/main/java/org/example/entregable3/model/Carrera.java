package org.example.entregable3.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.entregable3.utils.enums.Facultad;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
    public Carrera(){
    }


    public int getId_carrera() {
        return id_carrera;
    }

    public String getTitulo() {
        return titulo;
    }
}