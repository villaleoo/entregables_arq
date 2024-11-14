package MicroservicioViajes.Repository;

import MicroservicioViajes.DTO.TarifaDTO;
import MicroservicioViajes.Entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    @Query("SELECT t " +
            " FROM Tarifa t " +
            " WHERE t.fecha<= :hoy AND t.tipo = 'Normal'" +
            " ORDER BY t.fecha DESC")
    List<Tarifa> getTarifaNormalMasCercanaAHoy(@Param("hoy") Date hoy);

    @Query("SELECT t " +
            " FROM Tarifa t " +
            " WHERE t.fecha<= :hoy AND t.tipo = 'Extra' " +
            " ORDER BY t.fecha DESC")
    List<Tarifa> getTarifaExtraMasCercanaAHoy(@Param("hoy") Date hoy);
}
