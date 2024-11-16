package Usuario_servicio.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// La cuenta tendrá un número identificatorio y una
// fecha de alta.
@Entity
@Getter
public class Cuenta {
    @Id
    private Integer id;
    @Setter
    private String fecha_alta;
    @Setter
    private Integer mercadopago;
    @Setter
    private Double saldo;
    private String nombre_cuenta;
    @OneToMany(mappedBy = "cuenta")
    private List<Usuario> usuarios;
    public Cuenta(){}
    public Cuenta(Integer id, String fecha, Integer mp, String nc) {
        this.id = id;
        this.fecha_alta = fecha;
        this.nombre_cuenta = nc;
        this.mercadopago = mp;
        this.saldo = 0.0;
        this.usuarios = new ArrayList<>();
    }

    public void sumarSaldo(Double nuevoSaldo) {
        this.saldo += nuevoSaldo;
    }
    public void descontarSaldo(Double pago) {
        this.saldo -= pago;
    }
}
