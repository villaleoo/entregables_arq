package Monopatin_servicio.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
public class Monopatin {
    @Id
    private Integer id;
    @Setter
    private boolean disponible;
    @Setter
    private boolean mantenimiento;
    @Setter
    private Integer minutos_uso;
    @Setter
    private Integer minutos_pausa;
    @Setter
    private Integer km_uso;
    @Setter
    @ManyToOne
    private Parada parada;
    public Monopatin(){}

    public Monopatin(Integer id) {
        this.id = id;
        this.disponible = true;
        this.mantenimiento = false;
        this.minutos_uso = 0;
        this.minutos_pausa = 0;
        this.km_uso = 0;
    }
}
