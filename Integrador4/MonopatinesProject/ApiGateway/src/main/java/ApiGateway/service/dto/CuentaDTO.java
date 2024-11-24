package ApiGateway.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CuentaDTO {
    private String email;
    private Long idCuentaMP;
    private Boolean habilitada;
    private Date fechaAlta;
    private Set<String>roles;
}
