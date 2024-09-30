package integrador_2.DAO;

import java.util.List;

public interface Dao<Object> {
    public void insertar(Object o);
    public void borrar(int id);
    public Object traer(int id);
    public List<Object> traerTodos();
}
