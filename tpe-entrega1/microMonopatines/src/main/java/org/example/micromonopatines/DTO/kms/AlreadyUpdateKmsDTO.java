package org.example.micromonopatines.DTO.kms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.awt.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlreadyUpdateKmsDTO {
    private String message;
    private double kms;
}
