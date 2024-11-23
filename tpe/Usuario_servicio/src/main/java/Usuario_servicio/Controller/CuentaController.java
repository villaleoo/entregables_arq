package Usuario_servicio.Controller;

import Usuario_servicio.Dto.CuentaDTO;
import Usuario_servicio.Dto.UsuarioDTO;
import Usuario_servicio.Entity.Cuenta;
import Usuario_servicio.Entity.Usuario;
import Usuario_servicio.Service.CuentaService;
import Usuario_servicio.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService service;

    @GetMapping("")
    public ResponseEntity<List<CuentaDTO>> getCuentas() {
        return ResponseEntity.ok(service.getCuentas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getCuenta(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getCuenta(id));
    }
    @GetMapping("/{id}/usuarios")
    public ResponseEntity<List<UsuarioDTO>> getUsuariosDeCuenta(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getUsuariosDeCuenta(id));
    }
    @PostMapping("")
    public ResponseEntity<String> nuevaCuenta(@RequestBody Cuenta c) {
        try {
            service.nuevaCuenta(c);
            return new ResponseEntity<>("Cuenta creada exitosamente.", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Algo salio mal.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/deshabilitar/{id}")
    public ResponseEntity<String> deshabilitarCuenta(@PathVariable Integer id) {
        try {
            service.bajarCuenta(id);
            return new ResponseEntity<>("Cuenta dada de baja exitosamente.", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Algo salio mal.", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/habilitar/{id}")
    public ResponseEntity<String> habilitarCuenta(@PathVariable Integer id) {
        try {
            service.habilitarCuenta(id);
            return new ResponseEntity<>("Cuenta dada de baja exitosamente.", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Algo salio mal.", HttpStatus.BAD_REQUEST);
        }
    }
}
