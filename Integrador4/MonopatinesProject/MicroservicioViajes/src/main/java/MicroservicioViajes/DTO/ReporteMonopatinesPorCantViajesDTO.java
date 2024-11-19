package MicroservicioViajes.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReporteMonopatinesPorCantViajesDTO {
    private Long idMonopatin;
    private int anio;
    private long viajesRealizados;
}
