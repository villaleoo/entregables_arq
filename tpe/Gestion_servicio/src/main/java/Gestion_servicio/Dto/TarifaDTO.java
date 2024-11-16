package Gestion_servicio.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TarifaDTO {
    private Integer id;
    private Integer normal;
    private Integer extra;
    private String fecha;
}
