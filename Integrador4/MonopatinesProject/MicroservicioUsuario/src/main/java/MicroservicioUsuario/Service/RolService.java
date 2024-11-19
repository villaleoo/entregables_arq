package MicroservicioUsuario.Service;

import MicroservicioUsuario.DTO.RolDTO;
import MicroservicioUsuario.DTO.UsuarioDTO;
import MicroservicioUsuario.Entities.Rol;
import MicroservicioUsuario.Entities.Usuario;
import MicroservicioUsuario.Repository.RolRepository;
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
public class RolService {
    @Autowired
    private RolRepository rolRepostitory;

    public void add(RolDTO rol) {
        Rol u = new Rol();
        u.setTipo(rol.getTipo());
        rolRepostitory.save(u);
    }

    public RolDTO getById(Long id) {
        Rol r = rolRepostitory.findById(id).orElse(null);
        if (r != null)
            return new RolDTO(r.getTipo());
        throw new EntityNotFoundException("No se encontro rol  con id: " + id);
    }

    public Page<RolDTO> getAll(Pageable pageable) {
        Page<Rol> roles = rolRepostitory.findAll(pageable);
        List<RolDTO> res = new LinkedList<>();

        for (Rol r : roles)
            res.add(new RolDTO(r.getTipo()));

        return new PageImpl<>(res, pageable, roles.getTotalElements());
    }

    public void delete(Long id) {
        if (!rolRepostitory.existsById(id))
            throw new EntityNotFoundException("Rol no encontrado con id: " + id);
        rolRepostitory.deleteById(id);
    }

    public Rol update(Long id, RolDTO usuario) {
        if (!rolRepostitory.existsById(id))
            throw new EntityNotFoundException("Rol no encontrado con id: " + id);
        Rol r = rolRepostitory.findById(id).orElse(null);
        r.setTipo(usuario.getTipo());
        rolRepostitory.save(r);
        return r;
    }
}
