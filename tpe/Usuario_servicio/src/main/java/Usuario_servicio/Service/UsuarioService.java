package Usuario_servicio.Service;

import Usuario_servicio.Dto.UsuarioDTO;
import Usuario_servicio.Entity.Usuario;
import Usuario_servicio.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;
    public List<UsuarioDTO> getUsuarios() {
        return repo.traerUsuarios();
    }
    public UsuarioDTO getUsuario(Integer id) {
        return repo.traerUnUsuario(id);
    }
    public void nuevoUsuario(Usuario u) {
        repo.save(u);
    }
}
