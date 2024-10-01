package DTO;

import java.time.LocalDate;

public class Inscripcion {
    private Integer idEstudiante;
    private LocalDate fechaInscripcion;
    private LocalDate fechaEgreso;

    public Inscripcion(Integer idEstudiante, LocalDate fechaInscripcion, LocalDate fechaEgreso) {
        this.idEstudiante = idEstudiante;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaEgreso = fechaEgreso;
    }


    @Override
    public String toString() {
        return "Inscripcion{" +
                "idEstudiante=" + idEstudiante +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaEgreso=" + fechaEgreso +
                '}';
    }
}
