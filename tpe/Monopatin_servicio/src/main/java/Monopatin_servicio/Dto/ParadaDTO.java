package Monopatin_servicio.Dto;

import lombok.Getter;

@Getter
public class ParadaDTO {
    private Integer id;
    private String ciudad;
    private Integer x;
    private Integer y;

    public ParadaDTO(Integer id, String ciudad, Integer x, Integer y) {
        this.id = id;
        this.ciudad = ciudad;
        this.x = x;
        this.y = y;
    }
}
