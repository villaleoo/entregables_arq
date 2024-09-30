package Integrador1.Utils;

import Integrador1.Entities.Cliente;
import Integrador1.Entities.Factura;
import Integrador1.Entities.FacturaProducto;
import Integrador1.Entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class HelperDerby {
    private Connection conn = null;

    public HelperDerby() {//Constructor
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String uri = "jdbc:derby:MyDerbyDb;create=true";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri);

            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTables() throws SQLException {
        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet rs = dbMetaData.getTables(null, null, "FACTURA_PRODUCTO", null);

        if (rs.next()) {
            String dropFactura_Producto = "DROP TABLE  Factura_Producto";
            this.conn.prepareStatement(dropFactura_Producto).execute();
            this.conn.commit();
        }

        rs = dbMetaData.getTables(null, null, "FACTURA", null);
        if (rs.next()) {

            String dropFactura = "DROP TABLE  Factura";
            this.conn.prepareStatement(dropFactura).execute();
            this.conn.commit();
        }

        rs = dbMetaData.getTables(null, null, "CLIENTE", null);

        if (rs.next()) {
            String dropCliente = "DROP TABLE Cliente";
            this.conn.prepareStatement(dropCliente).execute();
            this.conn.commit();
        }

        rs = dbMetaData.getTables(null, null, "PRODUCTO", null);
        if (rs.next()) {
            String dropProducto = "DROP TABLE  Producto";
            this.conn.prepareStatement(dropProducto).execute();
            this.conn.commit();
        }

        rs.close();


    }

    public void createTables() throws SQLException {

        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet rs = dbMetaData.getTables(null, null, "CLIENTE", null);
        if (!rs.next()) { // Si la tabla no existe
            String tableCliente = "CREATE TABLE  Cliente(" +
                    "idCliente INT NOT NULL, " +
                    "nombre VARCHAR(50), " +
                    "email VARCHAR(150), " +
                    "CONSTRAINT idCliente PRIMARY KEY (idCliente)" +
                    ")";
            this.conn.prepareStatement(tableCliente).execute();
            this.conn.commit();
        }


        rs = dbMetaData.getTables(null, null, "FACTURA", null);

        if (!rs.next()) {
            String tableFactura = "CREATE TABLE Factura (" +
                    "idFactura  INT NOT NULL, " +
                    "idCliente  INT NOT NULL, " +
                    "PRIMARY KEY (idFactura), " +
                    "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)" +
                    ")";

            this.conn.prepareStatement(tableFactura).execute();
            this.conn.commit();
        }

        rs = dbMetaData.getTables(null, null, "PRODUCTO", null);

        if (!rs.next()) {
            String tableProducto = "CREATE TABLE Producto(" +
                    "idProducto INT NOT NULL, " +
                    "nombre VARCHAR(50), " +
                    "valor FLOAT, " +
                    "PRIMARY KEY (idProducto)" +
                    ")";
            this.conn.prepareStatement(tableProducto).execute();
            this.conn.commit();

        }

        rs = dbMetaData.getTables(null, null, "FACTURA_PRODUCTO", null);
        if (!rs.next()) {
            String tableFactura_Producto = "CREATE TABLE  Factura_Producto(" +
                    "idFactura  INT NOT NULL, " +
                    "idProducto  INT NOT NULL, " +
                    "cantidad  INT NOT NULL, " +
                    "CONSTRAINT pk_Factura_Producto PRIMARY KEY (idFactura, idProducto)," +
                    "FOREIGN KEY (idFactura) REFERENCES Factura (idFactura)," +
                    "FOREIGN KEY (idProducto) REFERENCES Producto (idProducto)" + ")" ;
            this.conn.prepareStatement(tableFactura_Producto).execute();
            this.conn.commit();
        }

        rs.close();


    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        try {
            System.out.println("Cargando DB...");
            for (CSVRecord row : getData("clientes.csv")) {
                if (row.size() >= 3) {
                    String idString = row.get(0);
                    String nombre = row.get(1);
                    String email = row.get(2);

                    if (!idString.isEmpty() && !nombre.isEmpty() && !email.isEmpty()) {
                        try {
                            int id = Integer.parseInt(idString);
                            Cliente cliente = new Cliente(id, nombre, email);
                            insertCliente(cliente, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de cliente: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Clientes insertados");

            for (CSVRecord row : getData("facturas.csv")) {
                if (row.size() >= 2) {
                    Integer idFactura = Integer.parseInt(row.get(0));
                    Integer idCliente = Integer.parseInt(row.get(1));

                    if (idFactura != null && idCliente != null) {
                        try {
                            Factura factura = new Factura(idFactura, idCliente);
                            insertFactura(factura, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de factura: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Facturas insertadas");

            for (CSVRecord row : getData("productos.csv")) {
                if (row.size() >= 3) {
                    Integer idProducto = Integer.parseInt(row.get(0));
                    String nombre = row.get(1);
                    String valor = row.get(2);
                    if (idProducto != null && !nombre.isEmpty() && !valor.isEmpty()) {
                        try {
                            Producto producto = new Producto(idProducto, nombre, Float.parseFloat(valor));
                            insertProducto(producto, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de cliente: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Productos insertados");


            for (CSVRecord row : getData("facturas-productos.csv")) {
                if (row.size() >= 3) {
                    String idString = row.get(0);
                    String numeroString = row.get(1);
                    String cantidad = row.get(2);
                    if (!idString.isEmpty() && !numeroString.isEmpty() && !cantidad.isEmpty()) {
                        try {
                            int id = Integer.parseInt(idString);
                            int numero = Integer.parseInt(numeroString);
                            FacturaProducto Factura_Producto = new FacturaProducto(id, numero, Integer.parseInt(cantidad));
                            insertFactura_Producto(Factura_Producto, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de cliente: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Facturas-Productos insertadas");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int insertCliente(Cliente cliente, Connection conn) throws Exception {
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertFactura(Factura factura, Connection conn) throws Exception {
        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertProducto(Producto producto, Connection conn) throws Exception {
        String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    private int insertFactura_Producto(FacturaProducto facturaProducto, Connection conn) throws Exception {
        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.setInt(2, facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null) {
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

