package org.example.micromonopatines.DTO.externals;

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

