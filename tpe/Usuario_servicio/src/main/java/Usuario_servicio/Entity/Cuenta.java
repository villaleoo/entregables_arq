package Usuario_servicio.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// La cuenta tendrá un número identificatorio y una
// fecha de alta.
@Entity
@Getter
public class Cuenta {
    @Id
    private Integer id;
    @Setter
    private String fecha_alta;
    @ManyToOne
    @MapsId("id")
    private MercadoPago mercadopago;
    public Cuenta(){}
    public Cuenta(Integer id, String fecha, MercadoPago mp) {
        this.id = id;
        this.fecha_alta = fecha;
        this.mercadopago = mp;
    }
}
