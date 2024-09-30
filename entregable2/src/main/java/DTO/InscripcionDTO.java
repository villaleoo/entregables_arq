package DTO;

import java.time.LocalDate;

public class InscripcionDTO {
    private Integer idEstudiante;
    private LocalDate fechaInscripcion;
    private LocalDate fechaEgreso;

    public InscripcionDTO(Integer idEstudiante, LocalDate fechaInscripcion, LocalDate fechaEgreso) {
        this.idEstudiante = idEstudiante;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaEgreso = fechaEgreso;
    }

    @Override
    public String toString() {
        return "InscripcionDTO{" +
                "idEstudiante=" + idEstudiante +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaEgreso=" + fechaEgreso +
                '}';
    }
}
