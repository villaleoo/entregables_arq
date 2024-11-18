package org.example.microusers.DTO;

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
    private RoleDTO role;
    private String username;
    private String email;
    private String password;
    private Date dischargeDate;
    private boolean isAvailable;

}
