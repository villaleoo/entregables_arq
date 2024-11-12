package org.example.microgestion.DTO.externals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinDTO {
    private String id_monopatin;
    private boolean isAvailable;
    private Point location;
    private double kms;
    private StopDTO stop_assign;
}