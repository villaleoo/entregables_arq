package org.example.microusers.DTO.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNewPaymentDTO {
    private String message;
    private UUID id_mp;
}
