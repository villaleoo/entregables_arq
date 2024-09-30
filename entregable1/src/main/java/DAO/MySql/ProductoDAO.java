package DAO.MySql;

import DAO.DAO;
import entities.Producto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements DAO<Producto> {
    private Connection conn;

    public ProductoDAO(Connection conn){
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
    public ArrayList<Producto> getAll() {
        String query = "SELECT * FROM Producto";
        PreparedStatement ps=null;
        ResultSet rs;
        ArrayList<Producto> results=new ArrayList<>();

        try{
            ps=conn.prepareStatement(query);
            rs= ps.executeQuery();

            while(rs.next()){
                int idProducto= rs.getInt("idProducto");
                String nombre = rs.getNString("nombre");
                Float valor = rs.getFloat("valor");

                Producto p = new Producto(idProducto,nombre, valor);
                results.add(p);
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

    public Producto getTopEarnerProduct (){
        String query= "SELECT p.idProducto, p.nombre, p.valor "+
                "FROM factura_producto fp "+
                "JOIN producto p ON fp.idProducto = p.idProducto "+
                "GROUP BY p.idProducto, p.nombre "+
                "ORDER BY SUM(fp.cantidad * p.valor) DESC "+
                "LIMIT 1";

        PreparedStatement ps=null;
        ResultSet rs;

        try{
            ps=conn.prepareStatement(query);
            rs= ps.executeQuery();

            if(rs.next()){
                int idProducto= rs.getInt("idProducto");
                String nombre = rs.getNString("nombre");
                Float valor = rs.getFloat("valor");

                return new Producto(idProducto,nombre,valor);

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

        return null;
    }


}
