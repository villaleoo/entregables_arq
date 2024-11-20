package org.example.micromonopatines.DTO.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.awt.*;
import java.awt.geom.Point2D;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    @NotNull(message = "Debe incluir la nueva ubicacion en un objeto 'location':{'x':coord, 'y':coord}.")
    private Point location;
}
