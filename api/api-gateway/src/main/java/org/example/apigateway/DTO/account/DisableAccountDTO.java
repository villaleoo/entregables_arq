package org.example.apigateway.DTO.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisableAccountDTO {
    private String username;
    private String email;
    private boolean isAvailable;
}
