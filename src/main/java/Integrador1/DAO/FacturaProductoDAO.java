package Integrador1.DAO;

import Integrador1.DTO.ProductoDTO;
import Integrador1.Entities.FacturaProducto;
import Integrador1.Entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FacturaProductoDAO {
    private Connection conn;

    public FacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertProducto(FacturaProducto factura_producto) {
        String query = "INSERT INTO Factura_Producto (idProducto, idFactura, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura_producto.getIdProducto());
            ps.setInt(2, factura_producto.getIdFactura());
            ps.setInt(3, factura_producto.getCantidad());
            ps.executeUpdate();
            System.out.println("Factura_Producto insertada exitosamente.");
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
        String query = "DELETE FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("Factura_Producto eliminado exitosamente.");
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

    public FacturaProducto find(Integer pk, Integer pk2) {
        String query = "SELECT f.idFactura, f.idProducto, f.cantidad " +
                "FROM Factura_Producto f " +
                "WHERE f.idFactura = ? AND f.idProducto = ?";
        FacturaProducto factura_productoById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            ps.setInt(2, pk2);
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                int cantidad = rs.getInt("cantidad");

                // Crear una nueva instancia de Persona con los datos recuperados de la consulta
                factura_productoById = new FacturaProducto(pk, pk2, cantidad);
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
        return factura_productoById;
    }

    public List<FacturaProducto> selectList() {
        List<FacturaProducto> res = new LinkedList<>();
        try {
            String select = "SELECT f.idFactura, f.idProducto, f.cantidad FROM Factura_Producto f";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FacturaProducto act = new FacturaProducto(rs.getInt("idFactura"), rs.getInt("idProducto"), rs.getInt("cantidad"));
                res.add(act);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;
    }


    public ProductoDTO findProductoMasRecaudo() {
        String query = "SELECT p.idProducto, p.nombre, p.valor, SUM(fp.cantidad) AS total_vendido, (p.valor * SUM(fp.cantidad)) AS recaudacion_total\n" +
                "FROM Producto p\n" +
                "JOIN Factura_Producto fp ON p.idProducto = fp.idProducto\n" +
                "GROUP BY p.idProducto, p.valor\n" +
                "ORDER BY recaudacion_total DESC";
        ProductoDTO productoMasRecaudo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                float valor = rs.getFloat("valor");
                int cantidad = rs.getInt("total_vendido");
                int recaudacionTotal = rs.getInt("recaudacion_total");

                productoMasRecaudo = new ProductoDTO(idProducto, nombre, valor, cantidad, recaudacionTotal);
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
        return productoMasRecaudo;
    }
}
