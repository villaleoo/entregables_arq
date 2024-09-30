package integrador_1.dao;

import integrador_1.entidades.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Factura_ProductoDAO implements DAO {
    private Connection conexion = null;
    public Factura_ProductoDAO(Connection c) {
        this.conexion = c;
    }
    @Override
    public void insertar(Object fp) {

        String insert = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES(?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(insert);
            ps.setInt(1,  ((Factura_Producto)fp).getIdFactura());
            ps.setInt(2,  ((Factura_Producto)fp).getIdProducto());
            ps.setInt(3,  ((Factura_Producto)fp).getCantidad());
            ps.executeUpdate();
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Factura_Producto traer(int id) {
        String select = "SELECT * FROM factura_producto where id=?";
        ResultSet rs = null;
        Factura_Producto factura_producto = null;
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            factura_producto = new Factura_Producto(id, rs.getInt("idProducto"), rs.getInt("cantidad"));
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return factura_producto;
    }
    @Override
    public List<Factura_Producto> traerTodos() {
        String select = "SELECT * FROM factura_producto";
        ResultSet rs = null;
        Factura_Producto factura_producto = null;
        List<Factura_Producto> factura_producto_lista = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            rs = ps.executeQuery();
            while(rs.next()) {
                factura_producto = new Factura_Producto(rs.getInt("idFactura"), rs.getInt("idProducto"), rs.getInt("cantidad"));
                factura_producto_lista.add(factura_producto);
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return factura_producto_lista;
    }
    @Override
    public void borrar(int id) {
        String select = "DELETE FROM factura_producto where id=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            ps.setInt(1, id);
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void crearTablaEnBBDD() {
        try {
            String table = "CREATE TABLE factura_producto("
                    + "idFactura INT, "
                    + "idProducto INT,"
                    + "cantidad INT,"
                    + "PRIMARY KEY(idFactura, idProducto))";

            conexion.prepareStatement(table).execute();
            conexion.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
