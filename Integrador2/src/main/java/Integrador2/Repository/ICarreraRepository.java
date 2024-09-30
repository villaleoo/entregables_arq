package Integrador2.Repository;

import Integrador2.DTO.ReporteDTO;
import Integrador2.Entities.Carrera;

import java.util.List;

public interface ICarreraRepository {
    List<Carrera> getCarrerasConEstudiantesInscriptos();
}
