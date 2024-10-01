package DAO.MySql;

import DAO.DAO;

import java.sql.Connection;
import java.util.List;

public class FacturaDAO implements DAO<FacturaDAO> {
    private Connection conn;

    public FacturaDAO(Connection conn){
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
    public List<FacturaDAO> getAll() {
        return List.of();
    }
}
