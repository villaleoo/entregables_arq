package Integrador3.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarreraInscriptosDTO {
    private String nombreCarrera;
    private Long cantInscriptos;

    @Override
    public String toString() {
        return
                "{nombreCarrera='" + nombreCarrera +
                        ", cantInscriptos=" + cantInscriptos + "}";
    }
}
