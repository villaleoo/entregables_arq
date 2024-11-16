package org.example.microviajesgestion.repository;

import org.example.microviajesgestion.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TravelRepository extends JpaRepository<Viaje,Long> {
    @Query("SELECT v FROM Viaje v WHERE v.state = true AND v.id_monopatin =:idMono")
    Optional<Viaje> findTravelActiveOfMono(String idMono);



    @Query("SELECT v.id_monopatin, COUNT(v) " +
            "FROM Viaje v " +
            "WHERE v.date_init >= :initDate AND v.date_init < :endDate " +
            "GROUP BY v.id_monopatin " +
            "HAVING COUNT(v) > :minTrips")
    Optional<List<String>> findAllIdMonopatinByFecha(Date initDate, Date endDate, Integer minTrips);
}
