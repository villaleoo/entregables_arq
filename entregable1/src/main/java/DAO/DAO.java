package DAO;

import java.util.List;

public interface DAO<T> {
    int insert();
    boolean delete();
    boolean update(int id);
    void find(int id);
    List<T> getAll();
}
