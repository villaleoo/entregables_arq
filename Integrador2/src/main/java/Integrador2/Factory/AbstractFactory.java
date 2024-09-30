package Integrador2.Factory;


import Integrador2.Repository.CarreraRepository;
import Integrador2.Repository.EstudianteRepository;
import Integrador2.Repository.InscripcionRepository;

public abstract class AbstractFactory {
    public static final int MYSQL = 1;
    public static final int DERBY = 2;

    public static AbstractFactory getRepositoryFactory(int factory) {
        switch (factory) {
            case MYSQL:
                return MySQLRepositoryFactory.getInstance();
            case DERBY:
                return DerbyRepositoryFactory.getInstance();
            default:
                return null;
        }
    }

    public abstract EstudianteRepository getEstudianteRepository();

    public abstract CarreraRepository getCarreraRepository();

    public abstract InscripcionRepository getInstruccionRepository();

}
