package Usuario_servicio.Repository;

import Usuario_servicio.Dto.UsuarioDTO;
import Usuario_servicio.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    //String nombre, String apellido, String email, String user, Integer celular
    @Query("select new Usuario_servicio.Dto.UsuarioDTO(u.nombre, u.apellido, u.email, u.nombre_usuario, u.nro_celular, u.cuenta.id)" +
            "from Usuario u")
    List<UsuarioDTO> traerUsuarios();
    @Query("select new Usuario_servicio.Dto.UsuarioDTO(u.nombre, u.apellido, u.email, u.nombre_usuario, u.nro_celular, u.cuenta.id)" +
            "from Usuario u where u.id=:id")
    UsuarioDTO traerUnUsuario(Integer id);
    @Query("select new Usuario_servicio.Dto.UsuarioDTO(u.nombre, u.apellido, u.email, u.nombre_usuario, u.nro_celular, :id)" +
            "from Usuario u where u.id in (select c.id from Cuenta c where c.id=:id)")
    List<UsuarioDTO> traerUsuariosDeCuenta(Integer id);
}
