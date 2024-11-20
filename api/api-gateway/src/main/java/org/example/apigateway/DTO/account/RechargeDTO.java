package org.example.apigateway.DTO.account;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeDTO {
    @NotNull(message = "Debe incluir el monto de recarga.")
    private double amount;
}
