package org.example.microgestion.repository;

import org.example.microgestion.model.PausasViaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface PauseTravelRepository extends JpaRepository<PausasViaje,Long> {
    @Query("SELECT p FROM PausasViaje p WHERE p.travel.id_travel = :id AND p.date_end_pause IS NULL")
    Optional<PausasViaje> findFirstByIdAndPausaIsNull(Long id);

    /*busca la pausa que finzalizo mas recientemente a la fecha que le paso por parametro*/
    @Query("SELECT p FROM PausasViaje p WHERE p.date_end_pause <= :dateEndTravel ORDER BY p.date_end_pause DESC")
    Optional<PausasViaje> findPauseMostRecent(Date dateEndTravel,Long id);
}