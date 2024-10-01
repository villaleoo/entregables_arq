package DAO.MySql;

import DAO.DAO;

import java.sql.Connection;
import java.util.List;

public class FacturaProductoDAO implements DAO<FacturaProductoDAO> {
    private Connection conn;

    public FacturaProductoDAO(Connection conn){
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
    public List<FacturaProductoDAO> getAll() {
        return List.of();
    }
}
