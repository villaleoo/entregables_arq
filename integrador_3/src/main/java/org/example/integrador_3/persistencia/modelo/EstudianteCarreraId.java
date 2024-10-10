package org.example.integrador_3.persistencia.modelo;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class EstudianteCarreraId implements Serializable {
    private Integer estudiante_id;
    private Integer carrera_id;

    public EstudianteCarreraId(){}

    public EstudianteCarreraId(Integer estudiante_id, Integer carrera_id) {
        this.estudiante_id = estudiante_id;
        this.carrera_id = carrera_id;
    }
}
