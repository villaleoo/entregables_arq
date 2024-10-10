package Integrador3.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstudianteDTO {
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private int documento;
    private String ciudad;
    private int nroLibreta;
}
