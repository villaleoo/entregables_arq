package integrador_1.dao;

import integrador_1.entidades.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAO{
    private Connection conexion = null;
    public ClienteDAO(Connection c) {
        this.conexion = c;
    }
    @Override
    public void insertar(Object c) {
        String insert = "INSERT INTO cliente (id, nombre, email) VALUES(?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(insert);
            ps.setInt(1,  ((Cliente) c).getIdCliente());
            ps.setString(2, ((Cliente) c).getNombre());
            ps.setString(3, ((Cliente) c).getEmail());
            ps.executeUpdate();
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cliente traer(int id) {
        String select = "SELECT * FROM cliente where id=?";
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                cliente = new Cliente(id, rs.getString("nombre"), rs.getString("email"));
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
    @Override
    public List<Cliente> traerTodos() {
//        String select = "SELECT * FROM cliente";
        String select = "select f.idCliente as id, count(fp.cantidad*p.valor) as facturacion \n" +
                "from factura f \n" +
                "join factura_producto fp\n" +
                "on fp.idFactura=f.idFactura\n" +
                "join producto p\n" +
                "on fp.idProducto=p.id\n" +
                "group by f.idCliente\n" +
                "order by facturacion desc;";
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            rs = ps.executeQuery();
            while(rs.next()) {
//                cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
                cliente = this.traer(rs.getInt("id"));
                clientes.add(cliente);
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
    @Override
    public void borrar(int id) {
        String select = "DELETE FROM cliente where id=?";
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
            String table = "CREATE TABLE cliente("
                    + "id INT, "
                    + "nombre VARCHAR(500),"
                    + "email VARCHAR(150),"
                    + "PRIMARY KEY(id))";

            conexion.prepareStatement(table).execute();
            conexion.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
