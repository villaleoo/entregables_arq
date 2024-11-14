package MicroservicioMonopatin.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParadaDTO {
    private int x;
    private int y;
    private String nombre;
    private String direccion;
    private int cantMonopatines;
}
