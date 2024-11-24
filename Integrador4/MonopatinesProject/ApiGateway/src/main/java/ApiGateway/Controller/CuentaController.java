package ApiGateway.Controller;

import ApiGateway.service.CuentaService;
import ApiGateway.service.dto.user.CuentaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService userService;
    private final CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid CuentaDTO userDTO) {
        final var id = userService.saveUser(userDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiGateway.service.dto.CuentaDTO> getById(@PathVariable Long id) {
        ApiGateway.service.dto.CuentaDTO cuenta = cuentaService.getCuentaById(id);
        if (cuenta != null)
            return new ResponseEntity<>(cuenta, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
