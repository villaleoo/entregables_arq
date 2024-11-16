package org.example.microviajesgestion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDTO {
    private double normalFee;
    private double additionalFee;
    private LocalDate dateValidity;
}