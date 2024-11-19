package MicroservicioViajes.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReporteMonopatinPausasDTO {
    private Long idMonopatin;
    private Long viajesRealizados;
    private double tiempo;
    private double KMsRecorridos;
}
