package com.arqui.entregable3.model.DTO;

import com.arqui.entregable3.utils.enums.Genero;

import java.time.LocalDate;

public class EstudianteDTO {
    private int id_persona;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private Genero genero;
    private int dni;
    private String ciudad_residencia;
    private String num_libreta;
    private int cant_carreras_inscriptas;

    public EstudianteDTO() {
        super();
    }

    public int getId_persona() {
        return id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public int getDni() {
        return dni;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public String getNum_libreta() {
        return num_libreta;
    }

    public int getCant_carreras_inscriptas() {
        return cant_carreras_inscriptas;
    }

    public EstudianteDTO(int id_persona, String nombre, String apellido, LocalDate fecha_nacimiento,
            Genero genero, int dni, String ciudad_residencia, String num_libreta, int cant_carreras_inscriptas) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.dni = dni;
        this.ciudad_residencia = ciudad_residencia;
        this.num_libreta = num_libreta;
        this.cant_carreras_inscriptas = cant_carreras_inscriptas;
    }
}