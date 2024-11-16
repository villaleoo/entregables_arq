package Gestion_servicio.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_viaje;
    private Integer id_usuario;
    private Integer id_cuenta;
    private Integer id_monopatin;
    private Integer id_parada_inicio;
    private Integer id_parada_final;
    private Date fecha_inicio;
    private Date fecha_final;
    private double kms_monopatin_inicio;
    private String estado;
    private double total_a_pagar;
    private boolean pausa;
    @OneToOne
    @JoinColumn(name="id_tarifa")
    private Tarifa tarifa;


    public Viaje(){}

    public Viaje(Integer id_user, Integer id_account, Integer id_monopatin, Integer id_stop_init,
                 Integer id_stop_end, Date date_init, Date date_end, double kms_monopatin_init) {
        this.id_usuario = id_user;
        this.id_cuenta = id_account;
        this.id_monopatin = id_monopatin;
        this.id_parada_inicio = id_stop_init;
        this.id_parada_final = id_stop_end;
        this.fecha_inicio = date_init;
        this.fecha_final = date_end;
        this.kms_monopatin_inicio=kms_monopatin_init;
        this.estado="activo";
        this.total_a_pagar=0;
        this.pausa=false;
        this.tarifa=null;

    }

    public void cambiarEstado(String e) {
        this.estado = e;
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "id_viaje=" + id_viaje +
                ", id_usuario=" + id_usuario +
                ", id_cuenta=" + id_cuenta +
                ", id_monopatin=" + id_monopatin +
                ", id_parada_inicio=" + id_parada_inicio +
                ", id_parada_final=" + id_parada_final +
                ", fecha_inicio='" + fecha_inicio + '\'' +
                ", fecha_final='" + fecha_final + '\'' +
                ", kms_monopatin_inicio=" + kms_monopatin_inicio +
                ", estado=" + estado +
                ", total_a_pagar=" + total_a_pagar +
                ", pausa=" + pausa +
                ", tarifa=" + tarifa +
                '}';
    }
}
