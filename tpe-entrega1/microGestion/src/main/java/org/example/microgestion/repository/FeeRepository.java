package org.example.microgestion.repository;

import org.example.microgestion.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface FeeRepository extends JpaRepository<Tarifa,Long> {
    @Query("SELECT t FROM Tarifa t WHERE t.dateValidity <= :date ORDER BY t.dateValidity DESC")
    Optional<Tarifa> findTopByDateBefore(Date date);
}
