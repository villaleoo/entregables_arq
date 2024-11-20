package org.example.apigateway.DTO.typeUsers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewClientAccountDTO {
    @NotBlank(message = "Debe incluir un username.")
    @Size(min=4,message = "El username debe contener al menos 4 caracteres")
    private String username;
    @NotBlank(message = "El email no puede estar vacio.")
    @Email(message = "Debe incluir un email valido.")
    private String email;
    @NotBlank(message = "Debe incluir una constraseña.")
    @Size(min=4,message = "La contraseña debe contener al menos 4 caracteres")
    private String password;
    @NotBlank(message = "Debe incluir un id de su cuenta de mercado pago.")
    private String id_mp;
}
