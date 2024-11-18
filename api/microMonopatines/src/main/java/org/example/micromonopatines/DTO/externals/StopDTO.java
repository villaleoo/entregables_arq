package org.example.micromonopatines.DTO.externals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopDTO {
    private Long id_stop;
    private String name;
    private String adress;
    private Point location;
}