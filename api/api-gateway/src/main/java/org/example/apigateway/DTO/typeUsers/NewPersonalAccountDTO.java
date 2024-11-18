package org.example.apigateway.DTO.typeUsers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPersonalAccountDTO {
    private String username;
    private String email;
    private String password;
}
