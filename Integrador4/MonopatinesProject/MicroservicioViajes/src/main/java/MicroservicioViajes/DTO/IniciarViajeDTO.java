package MicroservicioViajes.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IniciarViajeDTO {
    private Long idUsuario;
    private Long idCuenta;
    private Long idMonopatin;
    private Long idParadaInicio;
    private Long idParadaFin;

}
