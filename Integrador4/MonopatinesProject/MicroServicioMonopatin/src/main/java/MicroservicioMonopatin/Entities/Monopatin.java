package MicroservicioMonopatin.Entities;

import MicroservicioMonopatin.DTO.MonopatinDTO;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private String patenteMonopatin;

    @Column
    private Boolean disponible;

    @Column
    private Boolean enMantenimiento;


    public Monopatin(MonopatinDTO monopatinDTO) {
        this.patenteMonopatin = monopatinDTO.getPatenteMonopatin();
        this.disponible = monopatinDTO.getDisponible();
        this.enMantenimiento = monopatinDTO.getEnMantenimiento();
    }
}
