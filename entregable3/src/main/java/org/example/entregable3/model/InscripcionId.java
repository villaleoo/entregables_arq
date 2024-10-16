package org.example.entregable3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class InscripcionId implements Serializable {
    @Column(name="id_persona")
    private Integer estudianteId;
    @Column(name="id_carrera")
    private Integer carreraId;


    public InscripcionId() {}

    public InscripcionId(int estudianteId, int carreraId) {
        this.estudianteId = estudianteId;
        this.carreraId = carreraId;
    }
}