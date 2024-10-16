package org.example.entregable3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class InscripcionDTO {
    private Integer dniEstudiante;
    private Integer idCarrera;
    private LocalDate fechaInscripcion;
    private LocalDate fechaEgreso;


    @Override
    public String toString() {
        return "InscripcionDTO{" +
                "dniEstudiante=" + dniEstudiante +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaEgreso=" + fechaEgreso +
                '}';
    }
}