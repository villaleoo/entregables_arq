package org.example.apigateway.DTO.account;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAccountDTO {
    private Long id_account;
    private String username;
    private String email;
}
