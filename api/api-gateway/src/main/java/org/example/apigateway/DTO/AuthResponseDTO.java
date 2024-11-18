package org.example.apigateway.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    /*aca se devolveria el token y algo mas*/
    private String token;
    private final String type="Bearer: ";
}
