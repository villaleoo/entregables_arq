package org.example.microgestion.DTO.travels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microgestion.model.PausasViaje;
import org.example.microgestion.model.Tarifa;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelDTO {
    private Long id_travel;
    private Long id_user;
    private Long id_account;
    private String id_monopatin;
    private Long id_stop_init;
    private Long id_stop_end;
    private Date date_init;
    private Date date_end;
    private double kmsMonoInit;
    private double kmsTraveled;
    private int time;
    private boolean state;
    private double total_pay;
    private boolean isPause;
    private Tarifa fee;
    private double feeActive;
    private int quantityEndPauses;
}
