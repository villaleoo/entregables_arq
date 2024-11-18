package org.example.microgestion.DTO.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDebitDTO {
    private String description;
    private double totalDebit;
}
