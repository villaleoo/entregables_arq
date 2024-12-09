package org.example.microgestion.repository;

import feign.Param;
import org.example.microgestion.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FeeRepository extends JpaRepository<Tarifa,Long> {
    @Query("SELECT t FROM Tarifa t WHERE t.dateValidity <= :date ORDER BY t.dateValidity DESC")
    List<Tarifa> findLatestTarifa(@Param("date") Date date, Pageable pageable);
}
