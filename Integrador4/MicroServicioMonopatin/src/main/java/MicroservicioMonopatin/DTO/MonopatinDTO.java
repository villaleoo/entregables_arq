package MicroservicioMonopatin.DTO;

import MicroservicioMonopatin.Entities.Monopatin;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonopatinDTO {
    private String patenteMonopatin;
    private Boolean disponible;
    private Boolean enMantenimiento;

    public MonopatinDTO(Monopatin monopatin) {
        this.patenteMonopatin = monopatin.getPatenteMonopatin();
        this.disponible = monopatin.getDisponible();
        this.enMantenimiento = monopatin.getEnMantenimiento();
    }
}
