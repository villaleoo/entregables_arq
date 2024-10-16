package Integrador3.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import Integrador3.DTO.InscripcionDTO;
import Integrador3.DTO.ReporteDTO;
import Integrador3.Entities.Inscripcion;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    @Query("SELECT new Integrador3.DTO.ReporteDTO(i.carrera.idCarrera, c.nombreCarrera, YEAR(i.fechaInscripcion), COUNT(i.estudiante)  " +
            ", SUM(CASE WHEN i.fechaGraduacion IS NOT NULL THEN 1 ELSE 0 END))   " +
            "FROM Inscripcion i " +
            "JOIN i.carrera c " +
            "GROUP BY  i.carrera, YEAR(i.fechaInscripcion)" +
            "ORDER BY YEAR(i.fechaInscripcion), c.nombreCarrera DESC ")
    Page<ReporteDTO> getReporte(Pageable pageable);

    @Query("SELECT new Integrador3.DTO.InscripcionDTO(i.carrera.idCarrera, i.estudiante.documento, i.fechaInscripcion, i.fechaGraduacion)  " +
            "FROM Inscripcion i WHERE i.carrera.idCarrera =:idCarrera AND i.estudiante.documento =:idEstudiante ")
    InscripcionDTO findById(Long idCarrera, Long idEstudiante);

    @Modifying
    @Transactional
    @Query("DELETE FROM Inscripcion  i WHERE i.carrera.idCarrera =:idCarrera AND i.estudiante.documento = :idEstudiante")
    void deleteById(Long idCarrera, Long idEstudiante);

    @Modifying
    @Transactional
    @Query("UPDATE Inscripcion i SET" +
            " i.fechaInscripcion = :fechaInscripcion, i.fechaGraduacion = :fechaGraduacion" +
            " WHERE i.carrera.idCarrera = :idCarrera AND i.estudiante.documento = :idEstudiante")
    void update(Long idCarrera, Long idEstudiante, LocalDate fechaInscripcion, LocalDate fechaGraduacion);
}
