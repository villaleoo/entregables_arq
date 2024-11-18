package org.example.apigateway.DTO.typeUsers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewClientAccountDTO {
    private String username;
    private String email;
    private String password;
    private UUID id_mp;
}
