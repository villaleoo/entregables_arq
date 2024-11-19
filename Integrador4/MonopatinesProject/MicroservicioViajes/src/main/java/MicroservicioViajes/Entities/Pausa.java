package MicroservicioViajes.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pausa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPausa;
    @Column
    private Date fechaInicio;
    @Column
    private Date fechaFin;
    @Column
    private int limitePausa;
    @Column
    private double tiempoTotalPausa;


    public Pausa( Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.limitePausa = 15;
        this.tiempoTotalPausa = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pausa pausa = (Pausa) o;
        return limitePausa == pausa.limitePausa && Objects.equals(idPausa, pausa.idPausa) && Objects.equals(fechaInicio, pausa.fechaInicio) && Objects.equals(fechaFin, pausa.fechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPausa, fechaInicio, fechaFin, limitePausa);
    }
}
