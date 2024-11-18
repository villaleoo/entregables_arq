package org.example.apigateway.DTO.externals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPaymentDTO {
    private UUID id;
    private long id_account;
}

