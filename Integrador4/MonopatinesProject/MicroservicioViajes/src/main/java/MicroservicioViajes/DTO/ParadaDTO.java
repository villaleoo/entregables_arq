package MicroservicioViajes.DTO;

import lombok.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParadaDTO {
    private int x;
    private int y;
    private String nombre;
    private String direccion;
    private List<MonopatinDTO> monopatines;
    private int cantMonopatines;

    public ParadaDTO(int x, int y, String nombre, String direccion) {
        this.x = x;
        this.y = y;
        this.nombre = nombre;
        this.direccion = direccion;
        this.monopatines = new ArrayList<>();
    }

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

    public void addMonopatin(MonopatinDTO monopatinDTO) {
        monopatines.add(monopatinDTO);
    }

}
