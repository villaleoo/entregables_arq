package org.example.microusers.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String nameAccount;
    private LocalDate dischargeDate;
    private Integer balance;
    private Long id_mercadoPago;
    @JsonProperty("isAvailable")
    private boolean isAvailable;
}
