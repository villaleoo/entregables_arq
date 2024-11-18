package org.example.microgestion.DTO.travels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microgestion.DTO.payments.ResponseDebitDTO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloseTravelDTO {
    private Long id_travel;
    private double totalPay;
    private double kmsTraveled;
    private Date endDate;
    private ResponseDebitDTO debitEnd;
}