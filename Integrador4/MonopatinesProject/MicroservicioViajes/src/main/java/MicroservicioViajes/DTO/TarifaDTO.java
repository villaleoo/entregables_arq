package MicroservicioViajes.DTO;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TarifaDTO {
    private double tarifa;
    private String tipo;
    private Date fecha;
}
