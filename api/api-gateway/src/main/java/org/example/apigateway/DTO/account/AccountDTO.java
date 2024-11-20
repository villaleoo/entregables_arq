package org.example.apigateway.DTO.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.management.relation.Role;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String role;
    private String username;
    private String email;
    private Date dischargeDate;
    @JsonProperty("isAvailable")
    private boolean isAvailable;
}
