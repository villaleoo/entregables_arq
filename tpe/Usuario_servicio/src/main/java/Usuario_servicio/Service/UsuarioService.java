package Usuario_servicio.Service;

import Usuario_servicio.Dto.UsuarioDTO;
import Usuario_servicio.Entity.Usuario;
import Usuario_servicio.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository r;

    public Page<UsuarioDTO> getUsuarios(Pageable pageable) {
        Page<Usuario> list = r.findAll(pageable);
        List<UsuarioDTO> users = new LinkedList<>();
        for(Usuario u : list) {
            users.add(new UsuarioDTO(u.getNombre(), u.getApellido(), u.getEmail(),
                    u.getNombre_usuario(), u.getNro_celular())
            );
        }
        return new PageImpl<>(users, pageable, list.getTotalElements());
    }
}
