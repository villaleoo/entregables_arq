package com.arqui.entregable3.model.Entities;

import com.arqui.entregable3.utils.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@Entity
public class Estudiante extends Persona {
    
    @Column(unique = true, nullable = false)
    private String num_libreta;

    public Estudiante(String nombre, String apellido, LocalDate fecha_nacimiento, Genero genero, int dni,
            String ciudad_residencia, String num_libreta) {
        super(nombre, apellido, fecha_nacimiento, genero, dni, ciudad_residencia);
        this.num_libreta = num_libreta.toLowerCase().trim();
    }

    public String getNumLibreta() {
        return num_libreta;
    }

    public void setNum_libreta(String num_libreta) {
        this.num_libreta = num_libreta;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + this.getId() +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", residencia='" + ciudad_residencia + '\'' +
                ", libreta='" + num_libreta + '\'' +
                '}';
    }
}