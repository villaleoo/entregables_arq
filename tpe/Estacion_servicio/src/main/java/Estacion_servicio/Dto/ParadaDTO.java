package Estacion_servicio.Dto;

import Monopatin_servicio.Entity.Monopatin;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class ParadaDTO {
    private Integer id;
    private String ciudad;
    private String direccion;
    private List<Monopatin> monopatines;

    public ParadaDTO(Integer id, String ciudad, String direccion) {
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.monopatines = new ArrayList<>();
    }
}
