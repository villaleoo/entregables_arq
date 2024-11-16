package Gestion_servicio.Repository;

import Gestion_servicio.Entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {
    @Query("SELECT t FROM Tarifa t WHERE t.fecha <= CURRENT_DATE ORDER BY t.fecha DESC LIMIT 1")
    Tarifa getUltimaTarifa();
}
