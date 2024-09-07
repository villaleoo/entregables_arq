package DB;

import org.apache.commons.csv.CSVParser;

import java.sql.*;
import java.util.Map;

//contiene metodos para CREAR CONEXION, CREAR TABLAS, CARGAR DATOS DEFAULT Y CERRAR CONEXION
public class MySqlDB {
    public static Connection conn;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "systemDB";
    private static final String DB_URL = SERVER_URL + DB_NAME;
    private static final String PASSWORD = "";
    private static final String USER = "root";
    private static final String QUERY_CREATE = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
    private static final Map<String, CSVParser> MAP_CSV_LOAD = ReaderFilesCSV.formatCSVMap();



    private MySqlDB() {
    }

    //esta funcion es estatica para que pueda ser llamado sin necesidad de una instancia de la clase
    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }

        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();

            conn = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);

            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate(QUERY_CREATE);
                System.out.println("Base de datos lista para usar.");

            } catch (Exception e) {
                System.out.println(e);
            }

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            conn.setAutoCommit(false);
            conn.commit();
            System.out.println("Conectado a la base de datos : " + DB_NAME + ".");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);

        }

        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Conexion con la db finalizada.");
    }


    public static void createTables() throws SQLException {
        Boolean creation = Loader.createTables(conn);

        if(creation){
            System.out.println("Tablas: Cliente, Producto, Factura y Factura_Producto inicializadas.");
        }

    }

    //esta funcion carga valores iniciales a las tablas acorde a los archivos del mapa DEFAULT_VALUES_IN_TABLES
    public static void loadDefaultValues(){
        //si ya hay datos, no cargar valores para no pisarlos
        if(isDefaultValues()){
            return;
        }
        boolean insertIsCorrect = Loader.initInsertDb(MAP_CSV_LOAD , conn);

        if(insertIsCorrect){
            System.out.println("Se han agregado valores iniciales a las tablas.");
        }

    }

    //esta funcion asegura que no se precarguen datos si alguna tabla ya posee datos cargados (restrictivo)
    private static boolean isDefaultValues(){
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

}
