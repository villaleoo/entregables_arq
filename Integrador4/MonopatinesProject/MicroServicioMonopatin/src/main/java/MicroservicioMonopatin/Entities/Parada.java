package MicroservicioMonopatin.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;

    @Column
    private String direccion;

    @OneToMany
    private List<Monopatin> monopatines;

    @Column
    private int x;

    @Column
    private int y;

    public  void addMonopatin(Monopatin monopatin){
        monopatines.add(monopatin);
    }
}
