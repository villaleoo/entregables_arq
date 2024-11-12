package org.example.microusers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVisibleDataDTO {
    private String name;
    private String surname;
    private String phone;
    private String email;
}
