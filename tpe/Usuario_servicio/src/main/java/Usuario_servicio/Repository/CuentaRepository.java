package Usuario_servicio.Repository;

import Usuario_servicio.Dto.CuentaDTO;
import Usuario_servicio.Dto.UsuarioDTO;
import Usuario_servicio.Entity.Cuenta;
import Usuario_servicio.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    //Integer id, String fecha, MercadoPago mp, Usuario u
    @Query("select new Usuario_servicio.Dto.CuentaDTO(c.id, c.fecha_alta, c.mercadopago)" +
            "from Cuenta c")
    List<CuentaDTO> traerCuentas();
    @Query("select new Usuario_servicio.Dto.CuentaDTO(c.id, c.fecha_alta, c.mercadopago)" +
            "from Cuenta c where c.id=:id")
    CuentaDTO traerUnaCuenta(Integer id);
}
