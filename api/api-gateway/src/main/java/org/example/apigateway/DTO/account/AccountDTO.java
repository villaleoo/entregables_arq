package org.example.apigateway.DTO.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apigateway.DTO.RoleDTO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id_account;
    private RoleDTO role;
    private String username;
    private String email;
    private String password;
    private Date dischargeDate;
    @JsonProperty("isAvailable")
    private boolean isAvailable;
}
