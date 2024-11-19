package MicroservicioViajes.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParadaDTO paradaDTO = (ParadaDTO) o;
        return x == paradaDTO.x && y == paradaDTO.y && cantMonopatines == paradaDTO.cantMonopatines && Objects.equals(nombre, paradaDTO.nombre) && Objects.equals(direccion, paradaDTO.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, nombre, direccion, cantMonopatines);
    }
}
