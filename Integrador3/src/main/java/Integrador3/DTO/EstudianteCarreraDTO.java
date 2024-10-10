package Integrador3.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class EstudianteCarreraDTO {
    private Long idEstudiante;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private int documento;
    private String ciudad;
    private int nroLibreta;
    private Long idCarrera;
    private String nombreCarrera;





    @Override
    public String toString() {
        return "EstudianteCarreraDTO{" +
                "idEstudiante=" + idEstudiante +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", documento=" + documento +
                ", ciudad='" + ciudad + '\'' +
                ", nroLibreta=" + nroLibreta +
                ", idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                '}';
    }
}
