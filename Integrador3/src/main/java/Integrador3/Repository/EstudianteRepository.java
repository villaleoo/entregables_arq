package Integrador3.Repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import Integrador3.DTO.EstudianteCarreraDTO;
import Integrador3.DTO.EstudianteDTO;
import Integrador3.Entities.Estudiante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {



   @Query("SELECT new Integrador3.DTO.EstudianteDTO (e.documento,e.nombre, e.apellido, e.edad,e.genero, e.ciudad, e.nroLibreta)  " +
            "  FROM Estudiante e")
    List<EstudianteDTO> getEstudiantesOrderedBy(Sort sort);

    @Query("SELECT new Integrador3.DTO.EstudianteDTO ( e.documento,e.nombre, e.apellido, e.edad,e.genero, e.ciudad, e.nroLibreta)" +
            " FROM Estudiante e WHERE e.nroLibreta = :nroLibreta")
    EstudianteDTO getEstudianteByNroLibreta(int nroLibreta);

    @Query("SELECT new Integrador3.DTO.EstudianteDTO (e.documento,e.nombre, e.apellido, e.edad,e.genero,  e.ciudad, e.nroLibreta)" +
            " FROM Estudiante e WHERE e.genero = :genero")
    List<EstudianteDTO> getEstudiantesByGenero(String genero);

    @Query("SELECT new Integrador3.DTO.EstudianteCarreraDTO (e.documento, e.nombre, e.apellido, e.edad, e.genero," +
            " e.ciudad, e.nroLibreta," +
            "c.idCarrera, c.nombreCarrera) FROM Estudiante e " +
            "JOIN e.carreras  i " +
            "JOIN i.carrera c " +
            "WHERE c.nombreCarrera = :carrera AND e.ciudad= :ciudad")
    List<EstudianteCarreraDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad);
}
