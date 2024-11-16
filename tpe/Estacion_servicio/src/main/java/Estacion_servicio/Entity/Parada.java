package Estacion_servicio.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Parada {
    @Id
    private Integer id;
    @Setter
    private String ciudad;
    @Setter
    private Integer coordenada_x;
    @Setter
    private Integer coordenada_y;
    public Parada() {}

    public Parada(Integer id, String ciudad, Integer x, Integer y) {
        this.id = id;
        this.ciudad = ciudad;
        this.coordenada_x = x;
        this.coordenada_y = y;
    }

}
