package infraestructure;

import DAO.MySql.ClienteDAO;
import DAO.MySql.FacturaDAO;
import DAO.MySql.FacturaProductoDAO;
import DAO.MySql.ProductoDAO;

//esta seria la clase fabrica de primer nivel. Retorna Fabricas de MYSQL, DERBY O HIBERNATE
public abstract class DAOFactory {
    public static final int MYSQL_JDBC=1;
    public static final int DERBY_JDBC=2;
    //public static final int JPA_HIBERNATE=3;

    public static DAOFactory getDAOFactory (int whichFactory){
        return switch (whichFactory) {
            case MYSQL_JDBC -> MySqlDAOFactory.getInstance();
            //case DERBY_JDBC -> new DerbyDAOFactory();
            default -> null;
        };
    }

    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO ();
    public abstract FacturaProductoDAO getFacturaProductoDAO();
    public abstract ProductoDAO getProductoDAO();

}
