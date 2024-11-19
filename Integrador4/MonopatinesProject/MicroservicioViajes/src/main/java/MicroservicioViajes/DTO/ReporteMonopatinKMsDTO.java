package MicroservicioViajes.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReporteMonopatinKMsDTO {
    private Long idMonopatin;
    private double viajesRealizados;
    private double KMsRecorridos;
}
