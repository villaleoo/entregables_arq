package com.arqui.entregable3.model.Entities;

import com.arqui.entregable3.utils.enums.Genero;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
/*Herencia con Tabla Unida (Joined Table Inheritance)
Se almacena la clase base en una tabla y cada clase hija tiene su propia tabla que almacena sus atributos específicos.
Las tablas hijas contienen una clave foránea que apunta a la tabla de la clase base*/
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    protected int id_persona;
    @Column(nullable = false, length = 180)
    protected String nombre;
    @Column(nullable = false, length = 180)
    protected String apellido;
    @Column(nullable = false)
    protected LocalDate fecha_nacimiento;
    @Enumerated(EnumType.STRING)
    protected Genero genero;
    @Column(unique = true,nullable = false, length = 20)
    protected int dni;
    @Column(name = "ciudad")
    protected String ciudad_residencia;


    public Persona( String nombre, String apellido, LocalDate fecha_nacimiento, Genero genero, int dni, String ciudad_residencia){
        this.nombre=nombre.toLowerCase().trim();
        this.apellido=apellido.toLowerCase().trim();
        this.fecha_nacimiento=fecha_nacimiento;
        this.genero=genero;
        this.dni=dni;
        this.ciudad_residencia=ciudad_residencia.toLowerCase().trim();
    }


    public Persona() {
        super();
    }

    public int getId() {
        return id_persona;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }


}