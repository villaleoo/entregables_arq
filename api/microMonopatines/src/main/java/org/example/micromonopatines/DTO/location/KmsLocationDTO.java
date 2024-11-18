package org.example.micromonopatines.DTO.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KmsLocationDTO {
    private Point newLocation;
    private double newKms;

}
