package DTO;

import java.time.LocalDate;

public class EgresoDTO {
    private Integer idEstudiante;
    private LocalDate fechaEgreso;

    public EgresoDTO(Integer idEstudiante, LocalDate fechaEgreso) {
        this.idEstudiante = idEstudiante;
        this.fechaEgreso = fechaEgreso;
    }

    @Override
    public String toString() {
        return "EgresoDTO{" +
                "idEstudiante=" + idEstudiante +
                ", fechaEgreso=" + fechaEgreso +
                '}';
    }
}
