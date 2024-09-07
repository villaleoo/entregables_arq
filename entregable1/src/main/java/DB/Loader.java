package DB;

import entities.Cliente;
import entities.Factura;
import entities.FacturaProducto;
import entities.Producto;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.*;
import java.util.Map;

//se encarga de cargar datos default en la DB (necesita de una conexion establecida para funcionar)
public class Loader {

    public static boolean createTables (Connection conn) throws SQLException {
        Statement statement = conn.createStatement();

        String clienteQuery = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)" +
                ");";

        statement.executeUpdate(clienteQuery);

        String productoQuery = "CREATE TABLE IF NOT EXISTS Producto (" +
                "idProducto INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(45), " +
                "valor DECIMAL(10,2) " +
                ");";
        statement.executeUpdate(productoQuery);

        String facturaQuery = "CREATE TABLE IF NOT EXISTS Factura (" +
                "idFactura INT AUTO_INCREMENT PRIMARY KEY, " +
                "idCliente INT, " +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) " +
                ");";

        statement.executeUpdate(facturaQuery);

        String facturaProductoQuery = "CREATE TABLE IF NOT EXISTS Factura_Producto (" +
                "idFactura INT, " +
                "idProducto INT, " +
                "cantidad INT, " +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura), " +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) " +
                ");";

        statement.executeUpdate(facturaProductoQuery);

        conn.commit();

        return true;
    }

    public static boolean initInsertDb (Map<String, CSVParser> mapCSV, Connection conn){
        CSVParser customersParser= mapCSV.get("clientes.csv");
        CSVParser productsParser = mapCSV.get("productos.csv");
        CSVParser billParser = mapCSV.get("facturas.csv");
        CSVParser billProductParser = mapCSV.get("factura_producto.csv");


        if(customersParser != null){
            for(CSVRecord row : customersParser){
                if(row.size() >= 3 ){
                    int id = Integer.parseInt(row.get("idCliente"));
                    String nombre = row.get("nombre");
                    String email = row.get("email");
                    Cliente c = new Cliente(id,nombre,email);
                    insertCustomer(conn, c);
                }
            }
        }
        if(productsParser != null){
            for(CSVRecord row : productsParser){
                if(row.size() >= 3 ){
                    int id = Integer.parseInt(row.get("idProducto"));
                    String nombre = row.get("nombre");
                    Float valor = Float.parseFloat(row.get("valor"));
                    Producto p = new Producto(id,nombre,valor);
                    insertProduct(conn, p);
                }
            }
        }
        if(billParser != null){
            for(CSVRecord row : billParser){
                if(row.size() >= 2 ){
                    int id = Integer.parseInt(row.get("idFactura"));
                    int idCliente= Integer.parseInt(row.get("idCliente"));
                    Factura f = new Factura(id,idCliente);
                    insertBill(conn, f);
                }
            }
        }
        if(billProductParser != null){
            for(CSVRecord row : billProductParser){
                if(row.size() >= 3 ){
                    int idFactura = Integer.parseInt(row.get("idFactura"));
                    int idProducto= Integer.parseInt(row.get("idProducto"));
                    int q = Integer.parseInt(row.get("cantidad"));
                    FacturaProducto fp = new FacturaProducto(idFactura,idProducto,q);
                    insertBillProduct(conn, fp);
                }
            }
        }

        return true;
    }

    //funciones para insertar valores a las tablas cliente,producto,factura,facturaProducto
    private static boolean insertBillProduct(Connection conn, FacturaProducto fp){
        String insertQuery = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?,?,?)";
        PreparedStatement ps = null;

        try{
            ps=conn.prepareStatement(insertQuery);
            ps.setInt(1, fp.getIdFactura());
            ps.setInt(2, fp.getIdProducto());
            ps.setInt(3, fp.getCantidad());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar clientes default.");

            }

        } catch ( Exception e) {
            throw new RuntimeException(e);
        } finally {
            closePsAndCommit(conn, ps);
        }

        return true;
    }

    private static boolean insertProduct(Connection conn, Producto p){
        String insertQuery = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?,?)";
        PreparedStatement ps = null;

        try{
            ps=conn.prepareStatement(insertQuery);
            ps.setInt(1, p.getIdProducto());
            ps.setString(2,p.getNombre());
            ps.setFloat(3,p.getValor());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar clientes default.");

            }

        } catch ( Exception e) {
            throw new RuntimeException(e);
        } finally {
            closePsAndCommit(conn, ps);
        }

        return true;
    }

    private static boolean insertBill(Connection conn,Factura f){
        String insertQuery = "INSERT INTO Factura (idFactura, idCliente ) VALUES (?,?)";
        PreparedStatement ps = null;

        try{
            ps=conn.prepareStatement(insertQuery);
            ps.setInt(1, f.getIdFactura());
            ps.setInt(2, f.getIdCliente());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar clientes default.");

            }

        } catch ( Exception e) {
            throw new RuntimeException(e);
        } finally {
            closePsAndCommit(conn, ps);
        }

        return true;
    }

    private static boolean insertCustomer(Connection conn,Cliente c){
        String insertQuery = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)";
        PreparedStatement ps = null;

        try{
            ps=conn.prepareStatement(insertQuery);
            ps.setInt(1,c.getIdCliente());
            ps.setString(2,c.getNombre());
            ps.setString(3,c.getEmail());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar clientes default.");

            }

        } catch ( Exception e) {
            throw new RuntimeException(e);
        } finally {
            closePsAndCommit(conn, ps);
        }

        return true;
    }

    //esta funcion asegura que no se precarguen datos si alguna tabla ya posee datos cargados (restrictivo)
    private static boolean isDefaultValues(Connection conn){
        String query="SELECT EXISTS (SELECT 1 FROM Producto), "+
                "EXISTS (SELECT 1 FROM Factura), "+
                "EXISTS (SELECT 1 FROM Factura_Producto), "+
                "EXISTS (SELECT 1 FROM Cliente)";
        PreparedStatement ps=null;
        ResultSet rs = null;

        try{
            ps=conn.prepareStatement(query);
            rs = ps.executeQuery();

            if(rs.next()){

                //retorna true si alguna tabla ya contiene datos
                return rs.getBoolean(1) ||
                        rs.getBoolean(2) ||
                        rs.getBoolean(3) ||
                        rs.getBoolean(4);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    private static void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
