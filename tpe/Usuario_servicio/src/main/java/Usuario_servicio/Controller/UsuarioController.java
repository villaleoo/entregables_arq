package Usuario_servicio.Controller;

import Usuario_servicio.Dto.CuentaDTO;
import Usuario_servicio.Dto.UsuarioDTO;
import Usuario_servicio.Entity.Usuario;
import Usuario_servicio.Service.CuentaService;
import Usuario_servicio.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        return ResponseEntity.ok(service.getUsuarios());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getUsuario(id));
    }
    @PostMapping("/nuevo")
    public ResponseEntity<String> nuevoUsuario(@RequestBody Usuario u) {
        try {
            service.nuevoUsuario(u);
            return new ResponseEntity<>("Usuario creado exitosamente.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Algo salio mal.", HttpStatus.BAD_REQUEST);
        }
    }

}
