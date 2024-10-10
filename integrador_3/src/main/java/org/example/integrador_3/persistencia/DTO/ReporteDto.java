package org.example.integrador_3.persistencia.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ReporteDto {
    @Getter
    private Integer id;
    @Getter
    private String nombre;
    @Getter
    private Integer estudiante_id, inicio, graduacion;
    @Getter
    @Setter
    private List<EstudianteDto> inscriptos;
    @Getter
    @Setter
    private List<EstudianteDto> egresados;

    public ReporteDto() {
        super();
    }

    public ReporteDto(String nombre, Integer estudiante_id, Integer inicio, Integer graduacion) {
        this.nombre = nombre;
        this.estudiante_id = estudiante_id;
        this.inicio = inicio;
        this.graduacion = graduacion;
        this.inscriptos = new ArrayList<EstudianteDto>();
        this.egresados = new ArrayList<EstudianteDto>();
    }
}
