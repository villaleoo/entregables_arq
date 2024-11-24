package MicroservicioMonopatin.DTO;


import MicroservicioMonopatin.Entities.Monopatin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParadaDTO {
    private int x;
    private int y;
    private String nombre;
    private String direccion;
    private List<MonopatinDTO> monopatines;
    private int cantMonopatines;

    public ParadaDTO(int x, int y, String nombre, String direccion, int cantMonopatines) {
        this.x = x;
        this.y = y;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantMonopatines = cantMonopatines;
        this.monopatines = new ArrayList<>();
    }
}
