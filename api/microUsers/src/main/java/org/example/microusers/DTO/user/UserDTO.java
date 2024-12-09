package org.example.microusers.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id_user;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Date registrationDate;
}
