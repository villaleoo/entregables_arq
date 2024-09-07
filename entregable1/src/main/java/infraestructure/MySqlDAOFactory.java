package infraestructure;


import DAO.MySql.ClienteDAO;
import DAO.MySql.FacturaDAO;
import DAO.MySql.FacturaProductoDAO;
import DAO.MySql.ProductoDAO;
import DB.MySqlDB;

import java.sql.Connection;


//las clases fabricas de segundo nivel pueden ser 3 concretas: mysql (esta), derby, hibernate
public class MySqlDAOFactory extends DAOFactory{
    private final Connection conn = MySqlDB.createConnection();
    private static MySqlDAOFactory singleton =null;


    private MySqlDAOFactory(){}


    //esta funcion es estatica para que pueda ser accesible sin necesidad de una instancia previa de la clase. Es estatico para poder verificar y crear la instancia
    //la prinera vez que se llama y luego devolver esa misma instancia en las llamadas posteriores
    public static synchronized MySqlDAOFactory getInstance(){
        if(singleton == null){
            singleton = new MySqlDAOFactory();
        }
        return singleton;
    }


    //un DAO por cada tabla
    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAO(conn);
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAO(conn);
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() {
        return new FacturaProductoDAO(conn);
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new ProductoDAO(conn);
    }
}
