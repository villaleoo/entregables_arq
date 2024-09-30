package integrador_1.factories;

import integrador_1.dao.ClienteDAO;
import integrador_1.dao.FacturaDAO;
import integrador_1.dao.Factura_ProductoDAO;
import integrador_1.dao.ProductoDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlFactory extends AbstractFactory{

    public MySqlFactory(String driver, String uri) {
        super(driver, uri);
    }

    @Override
    public Connection abrirConexion() {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            System.exit(1);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URI, "root", "password");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    @Override
    public void cerrarConexion(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClienteDAO getCliente(Connection c) {
        return new ClienteDAO(c);
    }

    @Override
    public FacturaDAO getFactura(Connection c) {
        return new FacturaDAO(c);
    }

    @Override
    public Factura_ProductoDAO getFacturaProducto(Connection c) {
        return new Factura_ProductoDAO(c);
    }

    @Override
    public ProductoDAO getProducto(Connection c) {
        return new ProductoDAO(c);
    }
}
