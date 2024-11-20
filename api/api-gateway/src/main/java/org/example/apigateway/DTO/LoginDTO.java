package org.example.apigateway.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Debe incluir un username.")
    @Size(min=4,message = "El username debe contener al menos 4 caracteres")
    private String username;
    @NotBlank(message = "Debe incluir una constraseña.")
    @Size(min=4,message = "La contraseña debe contener al menos 4 caracteres")
    private String password;
}
