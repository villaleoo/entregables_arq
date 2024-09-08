import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearDB {
	
    public static void connection() {
        String url = "jdbc:mysql://localhost:3306/tp-arqui";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement smt = conn.createStatement()) {

            conn.setAutoCommit(false);
            createTables(conn);
            conn.commit();
            System.out.println("Tablas creadas exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        // Crea tabla Cliente si no existe
        String tablaCliente = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY AUTO_INCREMENT, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150))";
        conn.prepareStatement(tablaCliente).execute();

        // Crea tabla Factura si no existe
        String tablaFactura = "CREATE TABLE IF NOT EXISTS Factura (" +
                "idFactura INT PRIMARY KEY AUTO_INCREMENT, " +
                "idCliente INT, " +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";
        conn.prepareStatement(tablaFactura).execute();

        // Crea tabla Producto si no existe
        String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto (" +
                "idProducto INT PRIMARY KEY AUTO_INCREMENT, " +
                "nombre VARCHAR(45), " +
                "valor FLOAT)";
        conn.prepareStatement(tablaProducto).execute();

        // Crea tabla Factura_Producto si no existe
        String tablaFactura_Producto = "CREATE TABLE IF NOT EXISTS Factura_Producto (" +
                "idFactura INT, " +
                "idProducto INT, " +
                "cantidad INT, " +
                "PRIMARY KEY (idFactura, idProducto), " +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura), " +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
        conn.prepareStatement(tablaFactura_Producto).execute();
    }
}
