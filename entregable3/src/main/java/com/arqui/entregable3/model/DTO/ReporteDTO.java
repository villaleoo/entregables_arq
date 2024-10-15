package com.arqui.entregable3.model.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ReporteDTO {
    private Long idCarrera;
    private String nombreCarrera;
    private Long totalInscriptos; 
    private Long totalEgresados;  

    
    public ReporteDTO(Long idCarrera, String nombreCarrera, Long totalInscriptos, Long totalEgresados) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.totalInscriptos = totalInscriptos;
        this.totalEgresados = totalEgresados;
    }

    @Override
    public String toString() {
        return "ReporteDTO{" +
                "idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                ", totalInscriptos=" + totalInscriptos +
                ", totalEgresados=" + totalEgresados +
                '}';
    }
}
