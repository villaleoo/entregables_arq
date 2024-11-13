package org.example.microgestion.repository;


import org.example.microgestion.DTO.externals.monopatin.ReportTravelsMonoDTO;
import org.example.microgestion.DTO.reports.ReportMonopatinesDTO;
import org.example.microgestion.DTO.travels.TravelDTO;
import org.example.microgestion.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TravelRepository extends JpaRepository<Viaje,Long> {
    @Query("SELECT v FROM Viaje v WHERE v.state = true AND v.id_monopatin =:idMono")
    Optional<Viaje> findTravelActiveOfMono(String idMono);



    @Query("SELECT new org.example.microgestion.DTO.reports.ReportMonopatinesDTO(v.id_monopatin, COUNT(v) )" +
            "FROM Viaje v " +
            "WHERE v.date_init >= :initDate AND v.date_init < :endDate " +
            "GROUP BY v.id_monopatin " +
            "HAVING COUNT(v) >= :minTrips")
    Optional<List<ReportMonopatinesDTO>> findAllIdMonopatinByFecha(Date initDate, Date endDate, Integer minTrips);

    @Query("SELECT SUM(v.total_pay) FROM Viaje v WHERE v.date_init BETWEEN :initDate AND :endDate")
    Optional<Long> findTotalBilled(Date initDate,Date endDate);

    @Query("SELECT new org.example.microgestion.DTO.externals.monopatin.ReportTravelsMonoDTO( COUNT(v), COALESCE(SUM(p.minPause), 0) )" +
            "FROM Viaje v " +
            "LEFT JOIN v.pauses p " +
            "WHERE v.id_monopatin = :id")
    Optional<ReportTravelsMonoDTO> findDetailsTravelsOfMonopatin(String id);

    @Query("SELECT new org.example.microgestion.DTO.travels.TravelDTO(v.id_travel,v.id_user,v.id_account,v.id_monopatin,v.id_stop_init,v.id_stop_end,v.date_init,v.date_end,v.kms,v.state,v.total_pay,v.isPause,v.fee)  FROM Viaje v")
    List<TravelDTO> findAllProtected();



    @Modifying
    @Query("UPDATE Viaje v SET v.isPause = :state,v.total_pay= :totalPay WHERE v.id_travel = :id")
    void updateStatePause(boolean state,double totalPay,  Long id);
}