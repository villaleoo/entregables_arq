package Integrador2.DTO;

import javax.persistence.Column;

public class CarreraDTO {

    private String nombreCarrera;

    private Long cantInscriptos;

    public CarreraDTO(String nombreCarrera, Long cantInscriptos) {
        this.nombreCarrera = nombreCarrera;
        this.cantInscriptos = cantInscriptos;
    }


    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Long getCantInscriptos() {
        return cantInscriptos;
    }

    public void setCantInscriptos(Long cantInscriptos) {
        this.cantInscriptos = cantInscriptos;
    }

    @Override
    public String toString() {
        return
                "{nombreCarrera='" + nombreCarrera +
                        ", cantInscriptos=" + cantInscriptos + "}";
    }
}
