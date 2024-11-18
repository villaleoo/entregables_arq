package org.example.microgestion.DTO.fees;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDTO {
    private double normalFee;
    private double additionalFee;
    private Date dateValidity;
}