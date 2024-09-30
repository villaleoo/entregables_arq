package Integrador2.Entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class InscripcionID implements Serializable {

    private Long idEstudiante;

    private Long idCarrera;

    public InscripcionID() {}

    public InscripcionID(Long idEstudiante, Long idCarrera) {
        this.idEstudiante = idEstudiante;
        this.idCarrera = idCarrera;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }
}
