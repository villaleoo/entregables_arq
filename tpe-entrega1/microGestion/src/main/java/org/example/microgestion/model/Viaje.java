package org.example.microgestion.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Long id_travel;
    @Column(name = "id_usuario")
    private Long id_user;
    @Column(name = "id_cuenta")
    private Long id_account;
    private String id_monopatin;
    @Column(name = "id_parada_inicio")
    private Long id_stop_init;
    @Column(name = "id_parada_final")
    private Long id_stop_end;
    @Column(name = "fecha_inicio")
    private Date date_init;
    @Column(name = "fecha_final")
    private Date date_end;
    @Column(name = "kms_recorridos")
    private double kms;
    @Column(name = "estado")
    private boolean state;
    @Column(name = "total_pagado")
    private double total_pay;
    @Column(name = "esta_en_pausa")
    private boolean isPause;
    @ManyToOne
    @JoinColumn(name="id_tarifa")
    private Tarifa fee;
    @OneToMany(mappedBy = "travel")
    private List<PausasViaje> pauses;


    public Viaje(){}

    public Viaje(Long id_user, Long id_account, String id_monopatin, Long id_stop_init, Long id_stop_end, Date date_init, Date date_end, boolean state, double total_pay,boolean isPause,Tarifa fee,double kms) {
        this.id_user = id_user;
        this.id_account = id_account;
        this.id_monopatin = id_monopatin;
        this.id_stop_init = id_stop_init;
        this.id_stop_end = id_stop_end;
        this.date_init = date_init;
        this.date_end = date_end;
        this.state=state;
        this.total_pay=total_pay;
        this.isPause=isPause;
        this.fee=fee;
        this.kms=kms;
    }



    @Override
    public String toString() {
        return "Viajes{" +
                "id_travel='" + id_travel + '\'' +
                ", id_user='" + id_user + '\'' +
                ", id_account='" + id_account + '\'' +
                ", id_monopatin='" + id_monopatin + '\'' +
                ", id_stop_init='" + id_stop_init + '\'' +
                ", id_stop_end='" + id_stop_end + '\'' +
                ", date_init=" + date_init +
                ", date_end=" + date_end +
                ", state=" + state +
                '}';
    }




}
