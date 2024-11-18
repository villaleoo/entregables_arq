package org.example.apigateway.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenDTO {
    private boolean isAuthenticated;
    private String username;
    private List<String> authorities;

}