package DAO.MySql;

import DAO.DAO;
import entities.Cliente;
import entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Customer en concreto para base de datos mySQL
public class ClienteDAO implements DAO<ClienteDAO> {
    private Connection conn;

    public ClienteDAO(Connection conn){
        this.conn=conn;
    }
    @Override
    public int insert() {
        return 0;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public void find(int id) {

    }

    @Override
    public List getAll() {
        return List.of();
    }

    public ArrayList<Cliente> getListMostBilled (){
        String query = "SELECT c.*, COUNT(f.idFactura) AS cant_facturas " +
                "FROM Cliente c " +
                "JOIN Factura f ON c.idCliente = f.idCliente " +
                "GROUP BY c.idCliente, c.nombre, c.email " +
                "ORDER BY cant_facturas DESC";

        ArrayList<Cliente> results = new ArrayList<>();

        PreparedStatement ps=null;
        ResultSet rs;

        try{
            ps=conn.prepareStatement(query);
            rs= ps.executeQuery();

            while(rs.next()){
                int idCliente= rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                Cliente c = new Cliente(idCliente,nombre,email);
                results.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(ps!=null){
                    ps.close();
                }
                conn.commit();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return results;
    }
}
