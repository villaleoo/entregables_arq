package Integrador1.DAO;

import Integrador1.DTO.ClienteDTO;
import Integrador1.Entities.Cliente;
import Integrador1.Entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertCliente(Cliente cliente) {
        String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, cliente.getIdCliente()); // idPersona
            ps.setString(2, cliente.getNombre()); // nombre
            ps.setString(3, cliente.getEmail()); // email
            ps.executeUpdate();
            System.out.println("Cliente insertado exitosamente.");
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

    public boolean delete(Integer id)  {
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id); // Establece el valor del parámetro idCliente
            ps.executeUpdate();
            System.out.println("Cliente eliminado exitosamente.");
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

    public Cliente find(Integer pk) {
        String query = "SELECT c.idCliente, c.nombre, c.email " + "FROM Cliente c " + "WHERE c.idCliente = ?";
        Cliente clienteById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                // Crear una nueva instancia de Persona con los datos recuperados de la consulta
                clienteById = new Cliente(pk, nombre, email);
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
        return clienteById;
    }

    public List<Cliente> selectList() {
        List<Cliente> res = new LinkedList<>();
        try {
            String select = "SELECT c.idCliente, c.nombre, c.email  FROM Cliente c";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente act = new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("email"));
                res.add(act);
            }
            // conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<ClienteDTO> getClientWithMoreBills() {
        List<ClienteDTO> res = new LinkedList<>();
        try {
            String select = "SELECT c.idCliente, c.nombre, c.email, SUM(fp.cantidad * p.valor) AS totalFacturado " +
                    "FROM Cliente c " +
                    "JOIN Factura f ON c.idCliente = f.idCliente " +
                    "JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                    "JOIN Producto p ON fp.idProducto = p.idProducto " +
                    "GROUP BY c.idCliente, c.nombre, c.email " +
                    "ORDER BY totalFacturado DESC";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClienteDTO act = new ClienteDTO(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("email"), rs.getInt("totalFacturado"));
                res.add(act);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;
    }

}
