package Integrador2.Utils;

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

    public void dropTables() throws SQLException {
        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet rs = dbMetaData.getTables(null, null, "INSCRIPCION", null);

        if (rs.next()) {
            String dropTableInscripcion = "DROP TABLE  Inscripcion";
            this.conn.prepareStatement(dropTableInscripcion).execute();
            this.conn.commit();
        }

        rs = dbMetaData.getTables(null, null, "ESTUDIANTE", null);
        if (rs.next()) {

            String dropTableEstudiante = "DROP TABLE  Estudiante";
            this.conn.prepareStatement(dropTableEstudiante).execute();
            this.conn.commit();
        }

        rs = dbMetaData.getTables(null, null, "CARRERA", null);

        if (rs.next()) {
            String dropTableCarrera = "DROP TABLE Carrera";
            this.conn.prepareStatement(dropTableCarrera).execute();
            this.conn.commit();
        }

        rs.close();
    }
}

