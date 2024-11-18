package org.example.microgestion.DTO.externals.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id_account;
    private String username;
    @JsonProperty("isAvailable")
    private boolean isAvailable;
}
