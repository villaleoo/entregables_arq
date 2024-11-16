package org.example.microviajesgestion.repository;

import org.example.microviajesgestion.model.PausasViaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PauseTravelRepository extends JpaRepository<PausasViaje,Long> {
    @Query("SELECT p FROM PausasViaje p WHERE p.travel.id_travel = :id AND p.date_end_pause IS NULL")
    PausasViaje findFirstByIdAndPausaIsNull(Long id);
}
