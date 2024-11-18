package org.example.microusers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class MediosDePago {
    @Id
    @Column(name="id_mp")
    private UUID id;
    @Column(name="id_cuenta")
    private long id_account;
    @Column(name="saldo")
    private double balance;


    public MediosDePago() {}

    public MediosDePago(long id_account, double balance) {
        this.id_account = id_account;
        this.balance = balance;
    }


}
