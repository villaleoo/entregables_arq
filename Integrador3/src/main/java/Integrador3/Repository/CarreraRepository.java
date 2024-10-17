package Integrador3.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import Integrador3.DTO.CarreraInscriptosDTO;
import Integrador3.Entities.Carrera;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query("SELECT new Integrador3.DTO.CarreraInscriptosDTO (c.nombreCarrera, COUNT(i.carrera) ) " +
            "FROM Inscripcion i " +
            "JOIN i.carrera c " +
            "GROUP BY (c.nombreCarrera) " +
            "ORDER BY COUNT(i.carrera) DESC")
    Page<CarreraInscriptosDTO> getCarrerasConEstudiantesInscriptos(Pageable pageable);
}
