package Integrador1.Factory;

import Integrador1.DAO.ClienteDAO;
import Integrador1.DAO.FacturaDAO;
import Integrador1.DAO.FacturaProductoDAO;
import Integrador1.DAO.ProductoDAO;

public abstract class AbstractFactory {
    public static final int MYSQL = 1;
    public static final int DERBY = 2;

    public abstract ClienteDAO getClienteDAO();

    public abstract FacturaDAO getFacturaDAO();

    public abstract ProductoDAO getProductoDAO();

    public abstract FacturaProductoDAO getFacturaProductoDAO();


    public static AbstractFactory getDAOFactory(int factory) {
        switch (factory) {
            case MYSQL:
                return MySQLDAOFactory.getInstance();
            case DERBY:
                return DerbyDAOFactory.getInstance();
            default:
                return null;
        }
    }
}
