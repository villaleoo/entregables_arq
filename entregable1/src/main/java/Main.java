import integrador_1.dao.ClienteDAO;
import integrador_1.entidades.Cliente;
import integrador_1.factories.AbstractFactory;
import integrador_2.DAO.CarreraDao;
import integrador_2.DAO.EstudianteDao;
import integrador_2.DAO.InscripcionDao;
import integrador_2.DTO.CarreraDto;
import integrador_2.DTO.EstudianteDto;
import integrador_2.entidades.Carrera;
import integrador_2.entidades.Estudiante;
import integrador_2.entidades.Inscripcion;
import integrador_2.factories.MySqlFactory;
import integrador_1.helpers.BaseDeDatos;

import java.sql.Connection;
import java.util.List;
import java.util.Random;

public static void main (String [] args) {
    MySqlFactory factory = new MySqlFactory("integrador2");
    EstudianteDao estudiante = factory.instanciaEstudiante();
    CarreraDao carrera = factory.instanciaCarrera();
    InscripcionDao inscripcion = factory.instanciaInscripcion();

    List<CarreraDto> carreras = carrera.generarReporte();
    for(CarreraDto c : carreras)
        System.out.println(c);

}