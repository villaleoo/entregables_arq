package com.arqui.entregable3.model.Entities;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InscripcionId)) return false;

        InscripcionId i = (InscripcionId) o;
        return carreraId == i.carreraId && estudianteId.equals(i.estudianteId);
    }

    @Override
    public int hashCode() {
        return 31 * estudianteId.hashCode() + carreraId;
    }


}