package Integrador2.Factory;


import Integrador2.Repository.CarreraRepository;
import Integrador2.Repository.EstudianteRepository;
import Integrador2.Repository.InscripcionRepository;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyRepositoryFactory extends AbstractFactory {
    private static DerbyRepositoryFactory instance = null;

    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String uri = "jdbc:derby:MyDerbyDb;create=true";
    public static Connection conn;
    private EntityManager em;


    private DerbyRepositoryFactory() {
    }

    public static synchronized DerbyRepositoryFactory getInstance() {
        if (instance == null) {
            instance = new DerbyRepositoryFactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            conn = DriverManager.getConnection(uri);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EstudianteRepository getEstudianteRepository() {
        return EstudianteRepository.getInstance(em);
    }

    @Override
    public CarreraRepository getCarreraRepository() {
        return CarreraRepository.getInstance(em);
    }

    @Override
    public InscripcionRepository getInstruccionRepository() {
        return InscripcionRepository.getInstance(em);
    }
}
