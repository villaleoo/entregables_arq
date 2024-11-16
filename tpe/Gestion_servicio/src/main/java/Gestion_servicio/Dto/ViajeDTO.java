package Gestion_servicio.Dto;

import Gestion_servicio.Entity.Tarifa;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
public class ViajeDTO {
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
    private Tarifa tarifa;

    public ViajeDTO(){}

    public ViajeDTO(Integer id_user, Integer id_account, Integer id_monopatin, Integer id_stop_init,
                    Integer id_stop_end, Date date_init, Date date_end, double kms_monopatin_init,
                    double total_pay, Tarifa fee) {
        this.id_usuario = id_user;
        this.id_cuenta = id_account;
        this.id_monopatin = id_monopatin;
        this.id_parada_inicio = id_stop_init;
        this.id_parada_final = id_stop_end;
        this.fecha_inicio = date_init;
        this.fecha_final = date_end;
        this.kms_monopatin_inicio=kms_monopatin_init;
        this.estado="activo";
        this.total_a_pagar=total_pay;
        this.pausa=false;
        this.tarifa=fee;

    }
}
