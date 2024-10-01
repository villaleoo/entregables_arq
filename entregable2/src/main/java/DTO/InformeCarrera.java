package DTO;

import java.util.List;

public class InformeCarrera {
    private String nombre;
    private List<Inscripcion> inscripciones;
    private List<Egreso> egresos;

    public InformeCarrera(String nombre, List<Inscripcion> inscripciones, List<Egreso> egresos) {
        this.nombre = nombre;
        this.inscripciones = inscripciones;
        this.egresos = egresos;
    }

    @Override
    public String toString() {
        return "InformeCarrera{" +
                "nombre='" + nombre + '\'' +
                ", inscripciones=" + inscripciones +
                ", egresos=" + egresos +
                '}';
    }
}