package MicroservicioViajes.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double tarifa;

    @Column
    private String tipo;

    @Column
    private Date fecha;

    public Tarifa(double tarifa, String tipo, Date fecha) {
        this.tarifa = tarifa;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public Tarifa(double tarifa, String tipo) {
        this.tarifa = tarifa;
        this.tipo = tipo;
    }
}
