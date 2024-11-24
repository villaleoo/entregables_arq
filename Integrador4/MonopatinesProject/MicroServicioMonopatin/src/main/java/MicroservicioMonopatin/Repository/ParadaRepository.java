package MicroservicioMonopatin.Repository;

import MicroservicioMonopatin.DTO.MonopatinDTO;
import MicroservicioMonopatin.DTO.ParadaDTO;
import MicroservicioMonopatin.Entities.Monopatin;
import MicroservicioMonopatin.Entities.Parada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {
    @Query("SELECT new MicroservicioMonopatin.DTO.ParadaDTO(p.x, p.y, p.nombre, p.direccion, SIZE(p.monopatines))" +
            "FROM Parada p" +
            " ORDER BY ABS(p.x - :x) ASC, ABS (p.y - :y)")
    Page<ParadaDTO> getParadasCercanas(Pageable pageable, @Param("x") int x, @Param("y") int y);


}
