package org.example.microusers.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id_account;
    private String username;
    private String email;
    private String password;
    private Date dischargeDate;
    @JsonProperty("isAvailable")
    private boolean isAvailable;

}
