package Integrador3.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<EstudianteDTO> getEstudiantesOrderedBy(Pageable pageable);

    @Query("SELECT new Integrador3.DTO.EstudianteDTO (e.documento,e.nombre, e.apellido, e.edad,e.genero,  e.ciudad, e.nroLibreta)" +
            "FROM Estudiante e " +
            "JOIN e.carreras  c " +
            "WHERE (:documento IS NULL OR e.documento = :documento ) " +
            "AND (:nombre IS NULL OR e.nombre = :nombre)" +
            "AND (:apellido IS NULL OR e.apellido = :apellido) " +
            "AND (:edad IS NULL OR e.edad = :edad)" +
            "AND (:genero IS NULL OR e.genero = :genero) " +
            "AND (:ciudad IS NULL OR e.ciudad = :ciudad)" +
            "AND (:nroLibreta IS NULL OR e.nroLibreta = :nroLibreta)" +
            "AND (:idCarrera IS NULL OR c.carrera.idCarrera = :idCarrera)" +
            "AND (:nombreCarrera IS NULL OR c.carrera.nombreCarrera = :nombreCarrera)"
    )
    List<EstudianteDTO> getEstudiantesByAttribute(Long documento, String nombre, String apellido, Integer edad, String genero, String ciudad, Integer nroLibreta,
                                         Long idCarrera, String nombreCarrera);
}
