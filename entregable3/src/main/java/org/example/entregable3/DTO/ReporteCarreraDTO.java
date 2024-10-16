package org.example.entregable3.DTO;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class ReporteCarreraDTO {
    private String nombreCarrera;
    private List<InscripcionDTO> inscripciones;
    private List<EgresoDTO> egresos; // Asegúrate de crear este DTO también



    @Override
    public String toString() {
        return "\n\n\nReporteCarreraDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", \ninscripciones= \n" + inscripciones +
                ", \negresos=\n" + egresos +
                '}';
    }
}