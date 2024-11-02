package Monopatin_servicio.Repository;

import Monopatin_servicio.Entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Integer> {
}
