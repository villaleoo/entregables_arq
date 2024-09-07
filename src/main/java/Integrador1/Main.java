package Integrador1;


import Integrador1.DAO.ClienteDAO;
import Integrador1.DAO.FacturaDAO;
import Integrador1.DAO.FacturaProductoDAO;
import Integrador1.DAO.ProductoDAO;
import Integrador1.Entities.Cliente;
import Integrador1.Entities.FacturaProducto;
import Integrador1.Factory.AbstractFactory;
import Integrador1.Utils.HelperDerby;
import Integrador1.Utils.HelperMySQL;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {


     /*    HelperDerby dbDerby = new HelperDerby();

        dbDerby.dropTables();
        dbDerby.createTables();
        dbDerby.populateDB();
        dbDerby.closeConnection();*/

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


        System.out.println("Busco una Persona por id: ");
        Cliente personaById = cliente.find(2);
        System.out.println(personaById);
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println("Lista de direcciones: ");

        List<Cliente> listaClientes = cliente.selectList();
        for (Cliente cl : listaClientes) {
            System.out.println(cl);
        }

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");


    }
}