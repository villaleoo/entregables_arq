package Integrador1.DAO;

import Integrador1.Entities.Factura;
import Integrador1.Entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FacturaDAO {
    private Connection conn;

    public FacturaDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertFactura(Factura factura) {
        String query = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();
            System.out.println("Producto insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean delete(Integer id) {
        String query = "DELETE FROM Factura WHERE idFactura = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("Factura eliminada exitosamente.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Factura find(Integer pk) {
        String query = "SELECT p.idFactura, p.idProducto" +
                "FROM Producto p " +
                "WHERE p.idFactura = ?";
        Factura facturaById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                int idFactura = rs.getInt("idFactura");
                int idProducto = rs.getInt("idProducto");

                // Crear una nueva instancia de Persona con los datos recuperados de la consulta
                facturaById = new Factura(idFactura, idProducto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return facturaById;
    }

    public List<Factura> selectList() {
        List<Factura> res = new LinkedList<>();
        try {
            String select = "SELECT f.idFactura, f.idCliente FROM Factura f";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Factura act = new Factura(rs.getInt("idFactura"), rs.getInt("idProducto"));
                res.add(act);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;
    }
}
