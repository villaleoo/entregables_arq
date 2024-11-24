package MicroservicioViajes.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String nroCelular;
    private String email;
    private Long idCuenta;
}
