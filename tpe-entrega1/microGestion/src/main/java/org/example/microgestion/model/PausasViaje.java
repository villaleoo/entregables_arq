package org.example.microgestion.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class PausasViaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pausa")
    private Long id_pause;
    @ManyToOne
    @JoinColumn(name="id_viaje")
    private Viaje travel;
    @Column(name="hora_inicio")
    private Date date_init_pause;
    @Column(name="hora_fin")
    private Date date_end_pause;
    @Column(name="total_minutos")
    private Integer minPause;

    public PausasViaje() {}
    public PausasViaje(Long id_pause, Viaje viaje, Date date_init_pause, Date date_end_pause, Integer minPause) {
        this.id_pause = id_pause;
        this.travel = viaje;
        this.date_init_pause = date_init_pause;
        this.date_end_pause = date_end_pause;
        this.minPause = minPause;
    }


}