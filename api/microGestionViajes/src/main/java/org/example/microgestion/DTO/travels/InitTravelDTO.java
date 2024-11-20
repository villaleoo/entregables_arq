package org.example.microgestion.DTO.travels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitTravelDTO {
    @NotNull(message = "Debe ingresar un usuario valido.")
    private Long id_user;
    @NotNull(message = "Debe ingresar una cuenta valida.")
    private Long id_account;
    @NotBlank(message = "Debe ingresar un monopatin valido.")
    private String id_monopatin;
    @NotNull(message = "Debe ingresar una parada destino valida.")
    private Long id_stop_end;

}
