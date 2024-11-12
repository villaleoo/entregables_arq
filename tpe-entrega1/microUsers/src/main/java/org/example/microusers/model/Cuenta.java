package org.example.microusers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cuenta")
    private Long id_account;
    @Column(name="fecha_alta")
    private LocalDate dischargeDate;
    @Column(name="saldo")
    private Integer balance;
    private Long id_mercadoPago;
    @Column(name="nombre")
    private String nameAccount;
    @Column(name="disponible")
    private boolean isAvailable;

    public Cuenta() {}

    public Cuenta(LocalDate dischargeDate, Integer balance, Long id_mercadoPago, String nameAccount, boolean isAvailable) {
        this.dischargeDate = dischargeDate;
        this.balance = balance;
        this.id_mercadoPago = id_mercadoPago;
        this.nameAccount = nameAccount;
        this.isAvailable=isAvailable;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id_account=" + id_account +
                ", dischargeDate=" + dischargeDate +
                ", balance=" + balance +
                ", id_mercadoPago=" + id_mercadoPago +
                ", nameAccount='" + nameAccount + '\'' +
                '}';
    }
}
