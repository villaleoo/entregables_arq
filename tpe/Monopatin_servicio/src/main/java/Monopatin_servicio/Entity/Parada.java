package Monopatin_servicio.Entity;

import jakarta.persistence.*;
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
    private String direccion;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Monopatin> monopatines;
    public Parada() {}

    public Parada(Integer id, String ciudad, String direccion) {
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.monopatines = new ArrayList<>();
    }

}
