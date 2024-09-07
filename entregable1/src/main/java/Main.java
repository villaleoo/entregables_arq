import DAO.MySql.ClienteDAO;
import DAO.MySql.ProductoDAO;
import DB.MySqlDB;
import entities.Cliente;
import entities.Producto;
import infraestructure.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public static void main (String [] args) throws SQLException {
    MySqlDB.createConnection();
    MySqlDB.createTables();
    MySqlDB.loadDefaultValues();


    DAOFactory selFactory= DAOFactory.getDAOFactory(1);
    ProductoDAO p=selFactory.getProductoDAO();
    ClienteDAO c = selFactory.getClienteDAO();

    System.out.println("OBTENER PRODUCTO CON MAYOR RECAUDACION: ");
    Producto masRecaudado = p.getTopEarnerProduct();
    System.out.println("El producto que mas recuado es: ");
    System.out.println(masRecaudado);

    System.out.println("/////////////////////////////////////////////////");
    System.out.println("OBTENER LISTA DE CLIENTES EN BASE A LA CANTIDAD DE FACTURAS QUE SE LE HICIERON (MAS FACTURAS HECHAS PRIMERO): ");
    ArrayList<Cliente> masFacturados = c.getListMostBilled();
    System.out.println(masFacturados);

    MySqlDB.closeConnection();



}