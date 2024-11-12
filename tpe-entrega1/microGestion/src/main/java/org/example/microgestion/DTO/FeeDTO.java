package org.example.microgestion.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDTO {
    private double normalFee;
    private double additionalFee;
    private Date dateValidity;
}