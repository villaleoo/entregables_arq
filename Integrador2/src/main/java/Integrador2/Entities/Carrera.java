package Integrador2.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrera;

    @Column
    private String nombreCarrera;

    public Carrera() {
    }

    public Carrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    @Override
    public boolean equals(Object obj) {
        Carrera otro = (Carrera) obj;
        return this.getNombreCarrera().equals(otro.getNombreCarrera());
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                '}';
    }
}
