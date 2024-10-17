package Integrador3.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstudianteSearchDTO extends EstudianteDTO {
    private Long idCarrera;
    private String nombreCarrera;
}
