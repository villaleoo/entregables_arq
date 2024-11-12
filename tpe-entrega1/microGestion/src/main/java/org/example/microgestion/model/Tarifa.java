package org.example.microgestion.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tarifa")
    private Long id_fee;
    @Column(name="normal_por_kms")
    private double normalFee;
    @Column(name="extra_pausas")
    private double additionalFee;
    @Column(name="fecha_vigencia")
    private Date dateValidity;

    public Tarifa() {}

    public Tarifa(double normalFee, double additionalFee, Date dateValidity) {
        this.normalFee = normalFee;
        this.additionalFee = additionalFee;
        this.dateValidity = dateValidity;
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "id_fee=" + id_fee +
                ", normalFee=" + normalFee +
                ", addFee=" + additionalFee +
                ", dateValidity=" + dateValidity +
                '}';
    }
}
