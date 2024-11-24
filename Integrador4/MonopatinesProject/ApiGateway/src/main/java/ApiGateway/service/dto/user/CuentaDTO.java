package ApiGateway.service.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
public class CuentaDTO {

    @NotNull(message = "El email es un campo requerido.")
    @NotEmpty(message = "El email es un campo requerido.")
    private String email;

    @NotNull(message = "La contraseña es un campo requerido.")
    @NotEmpty(message = "La contraseña es un campo requerido.")
    private String password;

    private Long idCuentaMP;

    @NotNull(message = "Los roles son un campo requerido.")
    @NotEmpty(message = "Los roles son un campo requerido.")
    private Set<String> authorities;
}
