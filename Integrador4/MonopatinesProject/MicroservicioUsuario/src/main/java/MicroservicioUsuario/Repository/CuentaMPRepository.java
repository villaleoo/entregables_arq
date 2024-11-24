package MicroservicioUsuario.Repository;

import MicroservicioUsuario.Entities.CuentaMP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaMPRepository extends JpaRepository<CuentaMP, Long> {}
