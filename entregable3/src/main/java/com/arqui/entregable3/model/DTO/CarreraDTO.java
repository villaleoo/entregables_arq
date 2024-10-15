package com.arqui.entregable3.model.DTO;



public class CarreraDTO {
    private int id_carrera;
    private String titulo;
    

    public CarreraDTO() {}

    public CarreraDTO(int id_carrera, String titulo) {
        this.id_carrera = id_carrera;
        this.titulo = titulo;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public String getTitulo() {
        return titulo;
    }

}