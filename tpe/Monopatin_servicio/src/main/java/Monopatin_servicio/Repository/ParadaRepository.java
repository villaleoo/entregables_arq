package Monopatin_servicio.Repository;

import Monopatin_servicio.Entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Integer> {
}
