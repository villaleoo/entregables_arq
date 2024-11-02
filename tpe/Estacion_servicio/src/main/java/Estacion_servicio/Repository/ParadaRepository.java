package Estacion_servicio.Repository;

import Estacion_servicio.Entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Integer> {
}
