package org.example.apigateway.DTO.account;

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
