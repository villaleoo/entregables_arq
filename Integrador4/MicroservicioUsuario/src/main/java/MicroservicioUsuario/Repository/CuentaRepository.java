package MicroservicioUsuario.Repository;

import MicroservicioUsuario.Entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
