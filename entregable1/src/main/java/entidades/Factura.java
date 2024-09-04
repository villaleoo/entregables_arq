package entidades;


public class Factura {
    private int idFactura;
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
