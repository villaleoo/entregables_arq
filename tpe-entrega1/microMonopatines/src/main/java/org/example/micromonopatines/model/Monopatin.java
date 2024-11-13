package org.example.micromonopatines.model;


import lombok.Getter;
import lombok.Setter;

import org.example.micromonopatines.DTO.externals.StopDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.awt.*;


@Getter
@Setter
@Document(collection = "monopatines")
public class Monopatin {
    @Id
    private String id_monopatin;
    @Field("disponible")
    private boolean isAvailable;
    @Field("ubicacion")
    private Point location;
    private double kms;
    @Field("parada_asignada")
    private StopDTO stop_assign;


    public Monopatin(boolean isAvailable, Point location,double kms,StopDTO stop_assign) {
        this.isAvailable = isAvailable;
        this.location = location;
        this.kms=kms;
        this.stop_assign=stop_assign;
    }

    @Override
    public String toString() {
        return "Monopatin{" +
                "id_monopatin='" + id_monopatin + '\'' +
                ", isAvailable=" + isAvailable +
                ", location=" + location +
                '}';
    }
}
