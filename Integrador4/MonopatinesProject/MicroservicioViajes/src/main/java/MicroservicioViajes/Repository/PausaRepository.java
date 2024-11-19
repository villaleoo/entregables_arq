package MicroservicioViajes.Repository;

import MicroservicioViajes.Entities.Pausa;
import MicroservicioViajes.Entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PausaRepository extends JpaRepository<Pausa, Long> { }
