package org.example.microgestionparadas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;


@Entity
@Getter
@Setter
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_parada")
    private Long id_stop;
    @Column(name="nombre")
    private String name;
    @Column(name="direccion")
    private String adress;
    @Column(name="ubicacion_x")
    private double x;
    @Column(name="ubicacion_y")
    private double y;

    public Parada() {}

    public Parada(String name, String adress, Point location) {
        this.name = name;
        this.adress = adress;
        this.x = location.getX();
        this.y= location.getY();
    }

    @Override
    public String toString() {
        return "Parada{" +
                "id_stop=" + id_stop +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
