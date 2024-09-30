package integrador_1.dao;

public interface DAO<T> {
    public void insertar(T obj);
    public T traer(int id);
    public T traerTodos();
    public void borrar(int id);
    public void crearTablaEnBBDD();
}
