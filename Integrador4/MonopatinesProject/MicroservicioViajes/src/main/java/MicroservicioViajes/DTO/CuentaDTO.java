package MicroservicioViajes.DTO;

import lombok.*;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CuentaDTO {
    private String email;
    private Long idCuentaMP;
    private Boolean habilitada;
    private Date fechaAlta;
    private Set<String> roles;
}
