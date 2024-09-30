package integrador_1.factories;


import integrador_1.dao.ClienteDAO;
import integrador_1.dao.FacturaDAO;
import integrador_1.dao.Factura_ProductoDAO;
import integrador_1.dao.ProductoDAO;

import java.sql.Connection;

public abstract class AbstractFactory {
    protected final String DRIVER, URI;

    public AbstractFactory(String driver, String uri) {
        this.DRIVER = driver;
        this.URI = uri;
    }

    public abstract Connection abrirConexion();
    public abstract void cerrarConexion(Connection c);
    public abstract ClienteDAO getCliente(Connection c);
    public abstract FacturaDAO getFactura(Connection c);
    public abstract Factura_ProductoDAO getFacturaProducto(Connection c);
    public abstract ProductoDAO getProducto(Connection c);

}
