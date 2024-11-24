package MicroservicioUsuario.Service;

import MicroservicioUsuario.DTO.UsuarioDTO;
import MicroservicioUsuario.Entities.Usuario;
import MicroservicioUsuario.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private UsuarioRepository usuarioRepository;

    public void add(UsuarioDTO usuario) {
        Usuario u = new Usuario();
        u.setNombre(usuario.getNombre());
        u.setApellido(usuario.getApellido());
        u.setNroCelular(usuario.getNroCelular());
        u.setEmail(usuario.getEmail());
        u.setIdCuenta(usuario.getIdCuenta());
        usuarioRepository.save(u);
    }

    public UsuarioDTO getById(Long id) {
        Usuario u = usuarioRepository.findById(id).orElse(null);
        if (u != null)
            return new UsuarioDTO(u.getNombre(), u.getApellido(), u.getNroCelular(), u.getEmail(), u.getIdCuenta());
        throw new EntityNotFoundException("No se encontro usuario  con id: " + id);
    }

    public Page<UsuarioDTO> getAll(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        List<UsuarioDTO> res = new LinkedList<>();

        for (Usuario u : usuarios)
            res.add(new UsuarioDTO(u.getNombre(), u.getApellido(), u.getNroCelular(), u.getEmail(), u.getIdCuenta()));

        return new PageImpl<>(res, pageable, usuarios.getTotalElements());
    }

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id))
            throw new EntityNotFoundException("Usuario no encontrado con id: " + id);
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, UsuarioDTO usuario) {
        if (!usuarioRepository.existsById(id))
            throw new EntityNotFoundException("Usuario no encontrado con id: " + id);
        Usuario u = usuarioRepository.findById(id).orElse(null);
        u.setNombre(usuario.getNombre());
        u.setApellido(usuario.getApellido());
        u.setNroCelular(usuario.getNroCelular());
        u.setEmail(usuario.getEmail());
        u.setIdCuenta(usuario.getIdCuenta());
        usuarioRepository.save(u);
        return u;
    }
}
