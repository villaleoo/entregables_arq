package com.arqui.entregable3.model.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carrera;

    @Column(nullable = false, length = 180)
    private String titulo;



    public Carrera(String titulo) {
        this.titulo = titulo.toLowerCase().trim();
    }

    public Long getId_carrera() {
        return id_carrera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo.toLowerCase().trim();
    }


    @Override
    public String toString() {
        return "Carrera [id_carrera=" + id_carrera + ", titulo=" + titulo + "]";
    }


}