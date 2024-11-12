package org.example.microusers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientWithAccountDTO {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private LocalDate registrationDate;
    private Long id_account;
}
