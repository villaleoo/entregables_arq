package org.example.apigateway.DTO.externals;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private UUID id;
    private long id_account;
    private double balance;
}
