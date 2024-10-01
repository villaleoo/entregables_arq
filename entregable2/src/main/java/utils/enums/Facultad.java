package utils.enums;

public enum Facultad {
    EXACTAS,
    ECONOMICAS,
    HUMANAS,
    VETERINARIA,
    ARTE,
    OTRA;


    public static Facultad fromString(String facultad) {
        for (Facultad f : Facultad.values()) {
            if (f.name().equalsIgnoreCase(facultad)) {
                return f;
            }
        }

        return null;
    }
}
