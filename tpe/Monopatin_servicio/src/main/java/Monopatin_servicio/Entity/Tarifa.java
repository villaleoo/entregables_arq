package Monopatin_servicio.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {
    @Id
    private Integer id;
    @Setter
    private Integer normal;
    @Setter
    private Integer extra;
    @Setter
    private String fecha;
}
