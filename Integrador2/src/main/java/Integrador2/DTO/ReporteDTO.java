package Integrador2.DTO;

import Integrador2.Entities.Inscripcion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReporteDTO {
    private Long idCarrera;
    private String nombreCarrera;
    private HashMap<Integer, Integer> inscriptos;
    private HashMap<Integer, Integer> egresados;

    public ReporteDTO(Long idCarrera, String nombreCarrera, int anio, int inscriptos, int egresados) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = new HashMap<>();
        this.egresados = new HashMap<>();
        this.inscriptos.put(anio, inscriptos);
        this.egresados.put(anio, egresados);
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public HashMap<Integer, Integer> getInscriptos() {
        return new HashMap<>(inscriptos);
    }

    public HashMap<Integer, Integer> getEgresados() {
        return new HashMap<>(egresados);
    }

    public ReporteDTO(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    @Override
    public String toString() {
        return "\nReporteDTO{" +
                " nombreCarrera='" + nombreCarrera + '\'' +
                ", inscriptos=" + inscriptos +
                ", egresados=" + egresados +
                '}' + "\n";
    }
}
