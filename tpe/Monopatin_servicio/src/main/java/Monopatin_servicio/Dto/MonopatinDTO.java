package Monopatin_servicio.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonopatinDTO {

    private boolean disponible;

    private Integer minutos_uso;

    private Integer km_uso;

    public MonopatinDTO() {
    }

    public MonopatinDTO(boolean disponible, Integer minutos, Integer km) {

        this.disponible = disponible;
        this.minutos_uso = minutos;
        this.km_uso = km;
    }
}
