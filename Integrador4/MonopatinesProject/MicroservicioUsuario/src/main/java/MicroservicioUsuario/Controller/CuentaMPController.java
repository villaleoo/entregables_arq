package MicroservicioUsuario.Controller;

import MicroservicioUsuario.DTO.CuentaMPDTO;
import MicroservicioUsuario.Service.CuentaMPService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentasmp")
public class CuentaMPController {

    @Autowired
    private CuentaMPService cuentaService;

    @GetMapping("")
    public ResponseEntity<Page<CuentaMPDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer size,
                                                    @RequestParam(defaultValue = "credito, asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return new ResponseEntity<>(cuentaService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaMPDTO> getById(@PathVariable Long id) {
        CuentaMPDTO cuenta = cuentaService.getById(id);
        if (cuenta != null)
            return new ResponseEntity<>(cuenta, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody CuentaMPDTO cuenta) {
        try {
            cuentaService.add(cuenta);
            return new ResponseEntity<>("Cuenta creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error, ingresaste mal el nombre de una columna " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            cuentaService.delete(id);
            return new ResponseEntity<>("Cuenta eliminada exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La cuenta con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaMPDTO> update(@PathVariable Long id, @RequestBody CuentaMPDTO cuenta) {
        try {
            cuentaService.update(id, cuenta);
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
