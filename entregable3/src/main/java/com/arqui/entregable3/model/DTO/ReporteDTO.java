package com.arqui.entregable3.model.DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ReporteDTO {
    @Getter
    private Integer id;
    @Getter
    private String nombre;
    @Getter
    private Long id_estudiante;
    @Getter
    @Setter
    private LocalDate inicio, graduacion;
    @Getter
    @Setter
    private List<EstudianteDTO> inscriptos;
    @Getter
    @Setter
    private List<EstudianteDTO> egresados;

    public ReporteDTO() {
        super();
    }

    public ReporteDTO(String nombre, Long id_estudiante, LocalDate inicio, LocalDate graduacion) {
        this.nombre = nombre;
        this.id_estudiante = id_estudiante;
        this.inicio = inicio;
        this.graduacion = graduacion;
    }
}
