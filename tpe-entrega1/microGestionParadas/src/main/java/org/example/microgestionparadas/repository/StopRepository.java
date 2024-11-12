package org.example.microgestionparadas.repository;

import org.example.microgestionparadas.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopRepository extends JpaRepository<Parada,Long> {
}
