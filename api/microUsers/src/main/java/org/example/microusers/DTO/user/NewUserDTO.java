package org.example.microusers.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDTO {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Long id_account;
}
