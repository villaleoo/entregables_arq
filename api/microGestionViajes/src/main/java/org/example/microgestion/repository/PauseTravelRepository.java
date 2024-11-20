package org.example.microgestion.repository;

import org.example.microgestion.model.PausasViaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface PauseTravelRepository extends JpaRepository<PausasViaje,Long> {

}