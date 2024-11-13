package org.example.microgestionparadas.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisibleDataStopDTO {
    private String name;
    private String adress;
    private Point location;
}
