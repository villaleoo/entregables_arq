package org.example.microgestionparadas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisibleDataStopDTO {
    @NotBlank(message = "Debe incluir un nombre de parada.")
    private String name;
    @NotBlank(message = "Debe incluir una direccion de parada.")
    private String adress;
    @NotNull(message = "Debe incluir las coordenadas (x e y) de ubicacion de la parada.")
    private Point location;
}
