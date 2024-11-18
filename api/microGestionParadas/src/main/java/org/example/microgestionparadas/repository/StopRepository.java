package org.example.microgestionparadas.repository;

import org.example.microgestionparadas.model.Parada;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface StopRepository extends JpaRepository<Parada,Long> {


    @Query("SELECT p FROM Parada p " +
            "ORDER BY ABS(p.x - :x) ASC, ABS(p.y - :y) ASC")
    List<Parada> findTopMostNear( double x,  double y, Pageable pageable);
}
