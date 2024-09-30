package integrador_1.helpers;

import integrador_1.dao.ClienteDAO;
import integrador_1.dao.FacturaDAO;
import integrador_1.dao.Factura_ProductoDAO;
import integrador_1.dao.ProductoDAO;
import integrador_1.entidades.Cliente;
import integrador_1.entidades.Factura;
import integrador_1.entidades.Factura_Producto;
import integrador_1.entidades.Producto;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;

public class BaseDeDatos {
    private Connection conn;
    public BaseDeDatos(Connection c) {
        this.conn = c;
    }
    public void llenarBaseClientes() {
        CSV csv = new CSV();
        CSVParser parser = csv.transformar("entregable1/src/main/resources/data/clientes.csv");
        ClienteDAO cliente = new ClienteDAO(conn);
        for(CSVRecord row: parser) {
            Cliente nuevo = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
            cliente.insertar(nuevo);
        }
        System.out.println("Clientes terminado!");
    }
    public void llenarBaseFacturas() {
        CSV csv = new CSV();
        CSVParser parser = csv.transformar("entregable1/src/main/resources/data/facturas.csv");
        FacturaDAO factura = new FacturaDAO(conn);
        for(CSVRecord row: parser) {
            Factura f = new Factura(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente")));
            factura.insertar(f);
        }
        System.out.println("Facturas terminado!");
    }
    public void llenarBaseProductos() {
        CSV csv = new CSV();
        CSVParser parser = csv.transformar("entregable1/src/main/resources/data/productos.csv");
        ProductoDAO producto = new ProductoDAO(conn);
        for(CSVRecord row: parser) {
            Producto p = new Producto(Integer.parseInt(row.get("idProducto")), row.get("nombre"), Float.parseFloat(row.get("valor")));
            producto.insertar(p);
        }
        System.out.println("Productos terminado!");
    }
    public void llenarBaseFacturaProductos() {
        CSV csv = new CSV();
        CSVParser parser = csv.transformar("entregable1/src/main/resources/data/factura_producto.csv");
        Factura_ProductoDAO fac_prod = new Factura_ProductoDAO(conn);
        for(CSVRecord row: parser) {
            Factura_Producto fp = new Factura_Producto(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad")));
            fac_prod.insertar(fp);
        }
        System.out.println("Factura_productos terminado!");
    }
}
