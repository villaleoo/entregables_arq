package MicroservicioViajes.DTO;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonopatinDTO {
    private Long id;
    private String patenteMonopatin;
    private Boolean disponible;
    private Boolean enMantenimiento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonopatinDTO that = (MonopatinDTO) o;
        return Objects.equals(patenteMonopatin, that.patenteMonopatin);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(patenteMonopatin);
    }
}
