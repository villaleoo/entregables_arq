package Monopatin_servicio.Repository;

import Monopatin_servicio.Dto.MonopatinDTO;
import Monopatin_servicio.Entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Integer> {
    @Query("SELECT new Monopatin_servicio.Dto.MonopatinDTO(m.id, m.disponible, m.minutos_uso, m.minutos_pausa, m.km_uso) " +
            "FROM Monopatin m WHERE m.mantenimiento=true")
    List<MonopatinDTO> findMantenimiento();

    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.disponible=true")
    Integer getCantMonopatinesDisp();

    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.mantenimiento=true")
    Integer getCantMonopatinesMant();

    @Query("SELECT new Monopatin_servicio.Dto.MonopatinDTO(m.id, p.coordenada_x, p.coordenada_y)" +
            "FROM Monopatin m JOIN Parada p ON m.parada.id=p.id " +
            "WHERE (m.parada.coordenada_x BETWEEN :xMin AND :xMax) " +
            "AND (m.parada.coordenada_y BETWEEN :yMin AND :yMax) AND m.disponible=true")
    List<MonopatinDTO> monopatinesCercanos(Integer xMin, Integer yMin, Integer xMax, Integer yMax);
}
