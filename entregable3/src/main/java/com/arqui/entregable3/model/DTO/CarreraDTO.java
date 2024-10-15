package com.arqui.entregable3.model.DTO;


public class CarreraDTO {
    private String titulo;
    private long cantidadInscripciones;

    public CarreraDTO() {}

    public CarreraDTO(String titulo, long cantidadInscripciones) {
        this.titulo = titulo;
        this.cantidadInscripciones = cantidadInscripciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public long getCantidadInscripciones() {
        return cantidadInscripciones;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCantidadInscripciones(long cantidadInscripciones) {
        this.cantidadInscripciones = cantidadInscripciones;
    }
}