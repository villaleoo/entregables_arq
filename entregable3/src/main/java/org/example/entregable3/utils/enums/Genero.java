package org.example.entregable3.utils.enums;

public enum Genero {
    MASCULINO,
    FEMENINO,
    OTRO;


    public static Genero fromString(String genero) {
        for (Genero g : Genero.values()) {
            if (g.name().equalsIgnoreCase(genero)) {
                return g;
            }
        }

        return null;
    }
}