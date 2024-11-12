package org.example.micromonopatines.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.awt.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlreadyUpdateKmsDTO {
    private Point location;
    private double kms;
}
