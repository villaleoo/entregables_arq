package org.example.entregable3.DTO;

import lombok.*;
import org.example.entregable3.utils.enums.Facultad;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarreraDTO {
    private Integer idCarrera;
    private String nombreCarrera;
    private Facultad facultad;


}
