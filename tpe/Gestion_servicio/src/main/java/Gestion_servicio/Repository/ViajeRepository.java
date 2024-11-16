package Gestion_servicio.Repository;


import Gestion_servicio.Dto.ViajeDTO;
import Gestion_servicio.Entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ViajeRepository extends JpaRepository<Viaje,Long> {
    @Query("SELECT v FROM Viaje v WHERE v.estado = 'activo' AND v.id_monopatin =:idMono")
    Optional<Viaje> findTravelActiveOfMono(String idMono);

    @Query("SELECT v.id_monopatin, COUNT(v) " +
            "FROM Viaje v " +
            "WHERE v.fecha_inicio >= :initDate AND v.fecha_final < :endDate " +
            "GROUP BY v.id_monopatin " +
            "HAVING COUNT(v) > :minTrips")
    Optional<List<String>> findAllIdMonopatinByFecha(Date initDate, Date endDate, Integer minTrips);
    //Integer id, boolean disponible, Integer minutos, Integer mp, Integer km
    @Query("SELECT v.id_monopatin FROM Viaje v WHERE YEAR(v.fecha_final)=:anio AND COUNT(v.id_viaje)>=:viaje")
    List<Integer> monopatinesPorAnio(Integer viajes, Integer anio);

    @Query("SELECT COUNT(v.total_a_pagar) " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fecha_final)=:anio AND MONTH(v.fecha_final) BETWEEN :inicio AND :fin")
    Integer totalFacturadoEnAnioEntreMeses(Integer anio, Integer inicio, Integer fin);
    //Como administrador quiero consultar la cantidad de monopatines actualmente en operación,
    //versus la cantidad de monopatines actualmente en mantenimiento.
}
