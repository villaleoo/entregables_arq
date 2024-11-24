package MicroservicioMonopatin.Repository;

import MicroservicioMonopatin.DTO.MonopatinDTO;
import MicroservicioMonopatin.DTO.MonopatinDisponibleDTO;
import MicroservicioMonopatin.Entities.Monopatin;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.List;

public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Monopatin m SET " +
            "m.enMantenimiento = CASE " +
            "    WHEN m.enMantenimiento = true THEN false " +
            "    ELSE true " +
            "END, " +
            "m.disponible = CASE " +
            "    WHEN m.enMantenimiento = true THEN false " +
            "    ELSE true " +
            "END " +
            "WHERE m.id = :id")
    void setEnMantenimiento(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Monopatin m SET " +
            "m.disponible = CASE " +
            "    WHEN m.disponible = true THEN false " +
            "    ELSE true END " +
            "WHERE m.id = :id")
    void setDisponibilidad(@Param("id") Long id);

    @Query("SELECT new MicroservicioMonopatin.DTO.MonopatinDTO(m.id, m.patenteMonopatin, m.disponible, m.enMantenimiento) " +
            "FROM Monopatin m " +
            "WHERE (:patenteMonopatin IS NULL OR m.patenteMonopatin = :patenteMonopatin)" +
            "AND (:disponible IS NULL OR m.disponible = :disponible)" +
            "AND (:enMantenimiento IS NULL OR m.enMantenimiento = :enMantenimiento)")
    List<MonopatinDTO> getMonopatinesByAttribute(String patenteMonopatin,
                                                 Boolean disponible,
                                                 Boolean enMantenimiento);

    @Query("SELECT new MicroservicioMonopatin.DTO.MonopatinDisponibleDTO(COUNT(m.id)) " +
            "FROM Monopatin m " +
            "WHERE m.disponible = true " +
            "GROUP BY m.disponible")
    List<MonopatinDisponibleDTO> getMonopatinByDisponibilidad();

    @Query("SELECT new MicroservicioMonopatin.DTO.MonopatinDisponibleDTO(COUNT(m.id)) " +
            "FROM Monopatin m " +
            "WHERE m.enMantenimiento = true " +
            "GROUP BY m.enMantenimiento")
    List<MonopatinDisponibleDTO> getMonopatinByEnMantenimiento();
}
