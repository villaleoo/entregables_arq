package dao;

import javax.persistence.*;

@Entity
public class Cliente {
    @Id
    private int idCliente;
    @Column(length = 500)
    private String nombre;
    @Column(length = 150)
    private String email;
    public Cliente() {
        super();
    }

    public Cliente(String email, int idCliente, String nombre) {
        this.email = email;
        this.idCliente = idCliente;
        this.nombre = nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return STR."Cliente{idCliente=\{idCliente}, nombre='\{nombre}', email='\{email}'}";
    }

    @OneToOne(mappedBy = "cliente", optional = false)
    private Factura factura;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
