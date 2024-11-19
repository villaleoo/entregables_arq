package MicroservicioViajes.Repository;

import MicroservicioViajes.DTO.ReporteFacturadoPorRangoDTO;
import MicroservicioViajes.DTO.ReporteMonopatinKMsDTO;
import MicroservicioViajes.DTO.ReporteMonopatinPausasDTO;
import MicroservicioViajes.DTO.ReporteMonopatinesPorCantViajesDTO;
import MicroservicioViajes.Entities.Viaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    @Query("SELECT new MicroservicioViajes.DTO.ReporteMonopatinKMsDTO(v.idMonopatin," +
            "COUNT(v.idMonopatin) , " +
            "SUM(v.kmsRecorridos) ) " +
            "FROM Viaje v " +
            "GROUP BY (v.idMonopatin)" +
            "ORDER BY SUM(v.kmsRecorridos)")
    Page<ReporteMonopatinKMsDTO> getReporteMonopatinesPorKMs(Pageable pageable);

    @Query("SELECT new MicroservicioViajes.DTO.ReporteMonopatinPausasDTO(v.idMonopatin, COUNT(v.idMonopatin), " +
            "CASE WHEN :pausa = 0 THEN SUM(COALESCE(v.tiempo - p.tiempoTotalPausa, v.tiempo)) ELSE SUM(v.tiempo) END, " +
            "SUM(v.kmsRecorridos))" +
            "FROM Viaje v " +
            "LEFT JOIN v.pausas p " +
            "GROUP BY v.idMonopatin")
    Page<ReporteMonopatinPausasDTO> getReporteMantenimiento(Pageable pageable, @Param("pausa") int pausa);


    @Query("SELECT new MicroservicioViajes.DTO.ReporteMonopatinesPorCantViajesDTO(v.idMonopatin, " +
            "YEAR(v.fechaInicio), " +
            "COUNT(v.idMonopatin)) " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fechaInicio) = :anio " +
            "GROUP BY v.idMonopatin,YEAR(v.fechaInicio) " +
            "HAVING COUNT(v.idMonopatin) > :cantViajes " +
            "ORDER BY COUNT(v.idMonopatin) DESC")
    Page<ReporteMonopatinesPorCantViajesDTO> getReporteMonopatinesPorCantViajes(Pageable pageable, @Param("anio") int anio, @Param("cantViajes") int cantViajes);

    @Query("SELECT new MicroservicioViajes.DTO.ReporteFacturadoPorRangoDTO(YEAR(v.fechaInicio), SUM(v.precio)) " +
            "FROM Viaje v " +
            "WHERE MONTH(v.fechaInicio) BETWEEN :mesInicio AND :mesFin " +
            "AND YEAR(v.fechaInicio) = :anio " +
            "GROUP BY YEAR(v.fechaInicio)")
    Page<ReporteFacturadoPorRangoDTO> getReporteFacturadoEnAnioYMeses(Pageable pageable, @Param("anio") int anio, @Param("mesInicio") int mesInicio, @Param("mesFin") int mesFin);
}
