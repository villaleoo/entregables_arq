package DTO;

import java.time.LocalDate;
import java.util.List;

public class ReporteCarreraDTO {
    private String nombreCarrera;
    private List<InscripcionDTO> inscripciones;
    private List<EgresoDTO> egresos; // Asegúrate de crear este DTO también

    public ReporteCarreraDTO(String nombreCarrera, List<InscripcionDTO> inscripciones, List<EgresoDTO> egresos) {
        this.nombreCarrera = nombreCarrera;
        this.inscripciones = inscripciones;
        this.egresos = egresos;
    }

    @Override
    public String toString() {
        return "\n\n\nReporteCarreraDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", \ninscripciones= \n" + inscripciones +
                ", \negresos=\n" + egresos +
                '}';
    }
}