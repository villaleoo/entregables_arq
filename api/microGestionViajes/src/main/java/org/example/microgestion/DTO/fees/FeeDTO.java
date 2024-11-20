package org.example.microgestion.DTO.fees;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDTO {
    @NotNull(message = "Debe incluir una tarifa normal.")
    private double normalFee;
    @NotNull(message = "Debe incluir una tarifa adicional.")
    private double additionalFee;
    @NotNull(message = "Debe incluir una fecha de comienzo de vigencia.")
    private Date dateValidity;
}