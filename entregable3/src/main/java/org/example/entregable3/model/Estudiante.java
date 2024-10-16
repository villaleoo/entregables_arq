package org.example.entregable3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.example.entregable3.utils.enums.Genero;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
}