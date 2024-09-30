package integrador_1.dao;

import integrador_1.entidades.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements DAO {
    private Connection conexion = null;
    public FacturaDAO(Connection c) {
        this.conexion = c;
    }
    @Override
    public void insertar(Object f) {
        String insert = "INSERT INTO factura (idFactura, idCliente) VALUES(?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(insert);
            ps.setInt(1,  ((Factura)f).getIdFactura());
            ps.setInt(2, ((Factura)f).getIdCliente());
            ps.executeUpdate();
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Factura traer(int id) {
        String select = "SELECT * FROM factura where idFactura=?";
        ResultSet rs = null;
        Factura factura = null;
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            factura = new Factura(id, rs.getInt("idCliente"));
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return factura;
    }
    @Override
    public List<Factura> traerTodos() {
        String select = "SELECT * FROM factura";
        ResultSet rs = null;
        Factura factura = null;
        List<Factura> facturas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(select);
            rs = ps.executeQuery();
            while(rs.next()) {
                factura = new Factura(rs.getInt("idFactura"), rs.getInt("idCliente"));
                facturas.add(factura);
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facturas;
    }
    @Override
    public void borrar(int id) {
        String select = "DELETE FROM factura where id=?";
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
            String table = "CREATE TABLE factura("
                    + "idFactura INT, "
                    + "idCliente INT, "
                    + "PRIMARY KEY(idFactura))";

            conexion.prepareStatement(table).execute();
            conexion.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
