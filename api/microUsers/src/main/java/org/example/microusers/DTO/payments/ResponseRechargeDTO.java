package org.example.microusers.DTO.payments;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRechargeDTO {
    private long account;
    private double newAmount;
}
