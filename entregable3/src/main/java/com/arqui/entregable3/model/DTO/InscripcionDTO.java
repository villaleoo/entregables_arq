package com.arqui.entregable3.model.DTO;

import java.time.LocalDate;

public class InscripcionDTO {
    private int estudianteId;
    private int carreraId;
    private LocalDate fecha_inscripcion;
    private LocalDate fecha_egreso;

    public InscripcionDTO() {}

    public InscripcionDTO(int estudianteId, int carreraId, LocalDate fecha_inscripcion, LocalDate fecha_egreso) {
        this.estudianteId = estudianteId;
        this.carreraId = carreraId;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_egreso = fecha_egreso;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public int getCarreraId() {
        return carreraId;
    }

    public LocalDate getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public LocalDate getFecha_egreso() {
        return fecha_egreso;
    }
}
