package dao;

import javax.persistence.*;

@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFactura;
    @OneToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    public Factura() {
        super();   
    }

    public Factura(int idFactura, Cliente cliente) {
        this.idFactura = idFactura;
        this.cliente = cliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return STR."Factura{idFactura=\{idFactura}, cliente=\{cliente}}";
    }
}
