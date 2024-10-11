package com.arqui.entregable3.model.DTO;

import com.arqui.entregable3.utils.enums.Facultad;

public class CarreraDTO {
    private int id_carrera;
    private String titulo;
    private Facultad facultad;
    private int cant_inscriptos; // Solo el número de inscripciones

    public CarreraDTO() {}

    public CarreraDTO(int id_carrera, String titulo, Facultad facultad, int cant_inscriptos) {
        this.id_carrera = id_carrera;
        this.titulo = titulo;
        this.facultad = facultad;
        this.cant_inscriptos = cant_inscriptos;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public String getTitulo() {
        return titulo;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public int getCant_inscriptos() {
        return cant_inscriptos;
    }
}