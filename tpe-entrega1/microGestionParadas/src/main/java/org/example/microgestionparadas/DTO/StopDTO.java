package org.example.microgestionparadas.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopDTO {
    private String name;
    private String adress;
    private Point location;
}
