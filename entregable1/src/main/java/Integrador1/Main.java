package Integrador1;


import Integrador1.DAO.ClienteDAO;
import Integrador1.DAO.FacturaDAO;
import Integrador1.DAO.FacturaProductoDAO;
import Integrador1.DAO.ProductoDAO;
import Integrador1.DTO.ClienteDTO;
import Integrador1.DTO.ProductoDTO;
import Integrador1.Entities.Cliente;
import Integrador1.Entities.Factura;
import Integrador1.Entities.FacturaProducto;
import Integrador1.Entities.Producto;
import Integrador1.Factory.AbstractFactory;
import Integrador1.Utils.HelperDerby;
import Integrador1.Utils.HelperMySQL;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //DB DERBY:
    /*HelperDerby dbDerby = new HelperDerby();
        dbDerby.dropTables();
        dbDerby.createTables();
        dbDerby.populateDB();
        dbDerby.closeConnection(); */


        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB();
        dbMySQL.closeConnection();

        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        System.out.println();
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println();
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        FacturaProductoDAO factura_producto = chosenFactory.getFacturaProductoDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();


        System.out.println("EJ 3: Escriba un programa JDBC que retorne el producto que más recaudó. Se define\n" +
                "“recaudación” como cantidad de productos vendidos multiplicado por su valor.\n ");
        ProductoDTO prodMasRecaudo = factura_producto.findProductoMasRecaudo();
        System.out.println(prodMasRecaudo);

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

        System.out.println("EJ 4: Escriba un programa JDBC que imprima una lista de clientes, ordenada por a cuál se le\n" +
                "facturó más.");
        List<ClienteDTO> listaClientesMasFacturados = cliente.getClientWithMoreBills();
        for (ClienteDTO cl : listaClientesMasFacturados) {
            System.out.println(cl);
        }
    }
}