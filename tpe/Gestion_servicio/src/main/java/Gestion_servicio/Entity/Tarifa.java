package Gestion_servicio.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
