package org.example.microusers.DTO.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientVisibleDataDTO {
    private String name;
    private String surname;
    private String phone;
    private String email;
}
