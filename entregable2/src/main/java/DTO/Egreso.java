package DTO;

import java.time.LocalDate;

public class Egreso {
    private Integer idEstudiante;
    private LocalDate fecha;

    public Egreso(Integer idEstudiante, LocalDate fecha) {
        this.idEstudiante = idEstudiante;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Egreso{" +
                "idEstudiante=" + idEstudiante +
                ", fecha=" + fecha +
                '}';
    }
}
