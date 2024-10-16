package org.example.entregable3.DTO;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class EgresoDTO {
    private Integer idEstudiante;
    private LocalDate fechaEgreso;


    @Override
    public String toString() {
        return "EgresoDTO{" +
                "idEstudiante=" + idEstudiante +
                ", fechaEgreso=" + fechaEgreso +
                '}';
    }
}