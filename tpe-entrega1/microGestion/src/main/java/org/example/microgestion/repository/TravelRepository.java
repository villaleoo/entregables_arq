package org.example.microgestion.repository;


import org.example.microgestion.DTO.ReportMonopatinesDTO;
import org.example.microgestion.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TravelRepository extends JpaRepository<Viaje,Long> {
    @Query("SELECT v FROM Viaje v WHERE v.state = true AND v.id_monopatin =:idMono")
    Optional<Viaje> findTravelActiveOfMono(String idMono);



    @Query("SELECT new org.example.microgestion.DTO.ReportMonopatinesDTO(v.id_monopatin, COUNT(v) )" +
            "FROM Viaje v " +
            "WHERE v.date_init >= :initDate AND v.date_init < :endDate " +
            "GROUP BY v.id_monopatin " +
            "HAVING COUNT(v) >= :minTrips")
    Optional<List<ReportMonopatinesDTO>> findAllIdMonopatinByFecha(Date initDate, Date endDate, Integer minTrips);

    @Query("SELECT SUM(v.total_pay) FROM Viaje v WHERE v.date_init BETWEEN :initDate AND :endDate")
    Optional<Long> findTotalBilled(Date initDate,Date endDate);
}