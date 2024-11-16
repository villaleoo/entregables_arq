package org.example.microviajesgestion.DTO.externals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloseTravelDTO {
    private Long id_travel;
    private double totalPay;
    private Date endDate;
}