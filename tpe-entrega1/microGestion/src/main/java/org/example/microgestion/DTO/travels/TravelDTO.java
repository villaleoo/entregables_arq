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
    private long id_travel;
    private long id_user;
    private long id_account;
    private String id_monopatin;
    private long id_stop_init;
    private long id_stop_end;
    private Date date_init;
    private Date date_end;
    private double kms;
    private boolean state;
    private double total_pay;
    private boolean isPause;
    private Tarifa fee;
}
