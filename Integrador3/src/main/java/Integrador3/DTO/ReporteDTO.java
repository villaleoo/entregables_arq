package Integrador3.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReporteDTO {
    private Long idCarrera;
    private String nombreCarrera;
    private HashMap<Integer, Long> inscriptos;
    private HashMap<Integer, Long> egresados;

    public ReporteDTO(Long idCarrera, String nombreCarrera, int anio, Long inscriptos, Long egresados) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = new HashMap<>();
        this.egresados = new HashMap<>();
        this.inscriptos.put(anio, inscriptos);
        this.egresados.put(anio, egresados);
    }

    public HashMap<Integer, Long> getInscriptos() {
        return new HashMap<>(inscriptos);
    }

    public HashMap<Integer, Long> getEgresados() {
        return new HashMap<>(egresados);
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
