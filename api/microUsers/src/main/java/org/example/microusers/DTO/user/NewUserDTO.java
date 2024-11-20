package org.example.microusers.DTO.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDTO {
    @NotBlank(message = "Debe incluir un nombre.")
    @Size(min=3,message = "El nombre debe contener 3 caracteres o mas.")
    private String name;
    @NotBlank(message = "Debe incluir un apellido.")
    private String surname;
    @NotBlank(message = "Debe incluir un celular.")
    @Size(min=6 , message = "El telefono debe tener 6 numeros o mas.")
    private String phone;
    @NotBlank(message = "Debe incluir un email valido.")
    @Email(message = "Debe incluir un email valido.")
    private String email;
    private Long id_account;
}
