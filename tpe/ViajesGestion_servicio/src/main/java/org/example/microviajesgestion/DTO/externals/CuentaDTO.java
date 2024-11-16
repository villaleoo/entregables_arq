package org.example.microviajesgestion.DTO.externals;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {
    private Long id_account;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dischargeDate;
    private Integer balance;
    private Long id_mercadoPago;
    private String nameAccount;
    private boolean isAvailable;
}
