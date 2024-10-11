package com.arqui.entregable3.model.Entities;

import com.arqui.entregable3.utils.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante extends Persona{
    @Column(name = "num_libreta", unique = true, nullable = false)
    private String num_libreta;
    @OneToMany(fetch = FetchType.LAZY, mappedBy ="estudiante" )
    private List<Inscripcion> carreras_inscriptas;

    public Estudiante (String nombre, String apellido, LocalDate fecha_nacimiento, Genero genero, int dni, String ciudad_residencia, String num_libreta){
        super(nombre,apellido,fecha_nacimiento,genero,dni,ciudad_residencia);
        this.num_libreta=num_libreta.toLowerCase().trim();
        this.carreras_inscriptas=new ArrayList<>();
    }

    public Estudiante() {
        super();
    }

    public String getNum_libreta() {
        return num_libreta;
    }

    public void setNum_libreta(String num_libreta) {
        this.num_libreta = num_libreta;
    }

    public void addInscripcion(Inscripcion i){
        this.carreras_inscriptas.add(i);
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