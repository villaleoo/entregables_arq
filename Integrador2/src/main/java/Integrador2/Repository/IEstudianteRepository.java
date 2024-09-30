package Integrador2.Repository;

import Integrador2.DTO.EstudianteCarreraDTO;
import Integrador2.Entities.Carrera;
import Integrador2.Entities.Estudiante;

import java.util.List;

public interface IEstudianteRepository {

   // void addEstudiante(Estudiante estudiante); (Ya está en el BaseJPARepository).

    List<Estudiante> getEstudiantesOrderedBy(String criterio);

    Estudiante getEstudianteByNroLibreta(int nroLibreta);

    List<Estudiante> getEstudiantesByGenero(String genero);

    List<EstudianteCarreraDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad);


}
