package org.example.microgestionparadas.DTO.externals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microgestionparadas.model.Parada;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinDTO {
    private String id_monopatin;
    private boolean isAvailable;
    private Point location;
    private double kms;
    private Parada stop_assign;
}