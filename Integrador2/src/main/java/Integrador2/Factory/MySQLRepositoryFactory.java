package Integrador2.Factory;

import Integrador2.Repository.CarreraRepository;
import Integrador2.Repository.EstudianteRepository;
import Integrador2.Repository.InscripcionRepository;

import javax.persistence.EntityManager;
import java.sql.Connection;

public class MySQLRepositoryFactory extends AbstractFactory {
    private static MySQLRepositoryFactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/Integrador2";
    public static Connection conn;
    private EntityManager em;

    private MySQLRepositoryFactory() {
    }

    public static synchronized MySQLRepositoryFactory getInstance() {
        if (instance == null) {
            instance = new MySQLRepositoryFactory();
        }
        return instance;
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
