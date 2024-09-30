package integrador_1.dao;

import integrador_1.entidades.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements DAO {
    private Connection conexion = null;
    public ProductoDAO(Connection c) {
        this.conexion = c;
    }
    @Override
    public void insertar(Object p) {
        String insert = "INSERT INTO producto (id, nombre, valor) VALUES(?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(insert);
            ps.setInt(1,  ((Producto)p).getIdProducto());
            ps.setString(2, ((Producto)p).getNombre());
            ps.setFloat(3, ((Producto)p).getValor());
            ps.executeUpdate();
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Producto traer(int id) {
        String select = "SELECT * FROM producto where id=?";
        ResultSet rs = null;
        Producto producto = null;
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                producto = new Producto(id, rs.getString("nombre"), rs.getFloat("valor"));
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }
    @Override
    public List<Producto> traerTodos() {
        String select = "SELECT * FROM producto";
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            rs = ps.executeQuery();
            while(rs.next()) {
                producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("valor"));
                productos.add(producto);
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }
    @Override
    public void borrar(int id) {
        String select = "DELETE FROM producto where id=?";
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
            String table = "CREATE TABLE producto("
                    + "id INT, "
                    + "nombre VARCHAR(500),"
                    + "valor FLOAT,"
                    + "PRIMARY KEY(id))";

            conexion.prepareStatement(table).execute();
            conexion.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Producto traerProdQueMasRecaudo() {
        String select = "select p.id, (count(fp.cantidad)*p.valor) as recaudacion\n" +
                "from factura_producto fp\n" +
                "join producto p on fp.idProducto=p.id\n" +
                "group by p.id\n" +
                "order by recaudacion DESC\n" +
                "limit 1";
        ResultSet rs = null;
        Producto producto = null;
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                producto = this.traer(id);
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }
}
