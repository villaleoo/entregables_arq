package com.arqui.entregable3.model.DTO;

public class InscripcionIdDTO {
    private Integer estudianteId;
    private Integer carreraId;

    public InscripcionIdDTO() {
    }

    public InscripcionIdDTO(Integer estudianteId, Integer carreraId) {
        this.estudianteId = estudianteId;
        this.carreraId = carreraId;
    }

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public Integer getCarreraId() {
        return carreraId;
    }
}
