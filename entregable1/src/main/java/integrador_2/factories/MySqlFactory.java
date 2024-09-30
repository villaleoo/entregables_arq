package integrador_2.factories;

import integrador_2.DAO.CarreraDao;
import integrador_2.DAO.EstudianteDao;
import integrador_2.DAO.InscripcionDao;

public class MySqlFactory {
    protected String proyecto;
    public MySqlFactory(String p) {
        this.proyecto = p;
    }
    public CarreraDao instanciaCarrera() {
        return new CarreraDao(this.proyecto);
    }
    public EstudianteDao instanciaEstudiante() {
        return new EstudianteDao(this.proyecto);
    }
    public InscripcionDao instanciaInscripcion() {
        return new InscripcionDao(this.proyecto);
    }
}
