package MicroservicioUsuario.Repository;

import MicroservicioUsuario.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
