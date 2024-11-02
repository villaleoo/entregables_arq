package Usuario_servicio.Repository;

import Usuario_servicio.Entity.MercadoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoPagoRepository extends JpaRepository<MercadoPago, Integer> {
}
