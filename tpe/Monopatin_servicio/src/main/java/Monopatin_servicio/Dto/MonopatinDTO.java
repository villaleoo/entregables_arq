package Monopatin_servicio.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonopatinDTO {
    private Integer id;

    private boolean disponible;

    private Integer minutos_uso;

    private Integer minutos_pausa;

    private Integer km_uso;

    private Integer x;

    private Integer y;

    public MonopatinDTO() {
    }
    public MonopatinDTO(Integer id, Integer x, Integer y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public MonopatinDTO(Integer id, boolean disponible, Integer minutos, Integer mp, Integer km, Integer x, Integer y) {
        this.id = id;
        this.disponible = disponible;
        this.minutos_uso = minutos;
        this.minutos_pausa = mp;
        this.km_uso = km;
        this.x = x;
        this.y = y;
    }
    public MonopatinDTO(Integer id, boolean disponible, Integer minutos, Integer mp, Integer km) {
        this.id = id;
        this.disponible = disponible;
        this.minutos_uso = minutos;
        this.minutos_pausa = mp;
        this.km_uso = km;
    }
    public MonopatinDTO(Integer id, boolean disp) {
        this.id = id;
        this.disponible = disp;
    }
    public MonopatinDTO(boolean disponible, Integer minutos, Integer mp) {
        this.disponible = disponible;
        this.minutos_uso = minutos + mp;
    }
    public MonopatinDTO(boolean disponible, Integer km) {
        this.disponible = disponible;
        this.km_uso = km;
    }
}
