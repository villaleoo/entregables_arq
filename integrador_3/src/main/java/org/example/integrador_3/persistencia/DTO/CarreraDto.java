package org.example.integrador_3.persistencia.DTO;

//import integrador_2.entidades.Inscripcion;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CarreraDto {

    private Integer id;
    @Getter
    private String nombre;
    private Integer anios_duracion;
    private Integer cantidad_estudiantes;
    private List<EstudianteDto> estudiantes;

    public CarreraDto() {
        super();
    }

    public CarreraDto(Integer id, String nombre, Integer anios_duracion) {
        this.id = id;
        this.nombre = nombre;
        this.anios_duracion = anios_duracion;
        this.estudiantes = new ArrayList<EstudianteDto>();
    }

    public CarreraDto(Integer id, String nombre, Integer anios_duracion, Long cant) {
        this.id = id;
        this.nombre = nombre;
        this.anios_duracion = anios_duracion;
        this.cantidad_estudiantes = cant.intValue();
        this.estudiantes = new ArrayList<EstudianteDto>();
    }

    public List<EstudianteDto> estudiantes() {
        return estudiantes;
    }

    //    @Override
//    public String toString() {
//        return
//                STR."id = \{id}, nombre = '\{nombre}', anios_duracion = \{anios_duracion}, \n\{estudiantes}\n";
//    }
//
//    public String mostrarPorCantidadEstudiantes() {
//        return
//                STR."id=\{id}, nombre='\{nombre}', anios_duracion=\{anios_duracion}, cantidad_estudiantes=\{cantidad_estudiantes}";
//    }
}
