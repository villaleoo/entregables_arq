package MicroservicioViajes.Controller;

import MicroservicioViajes.DTO.PausaDTO;
import MicroservicioViajes.DTO.TarifaDTO;
import MicroservicioViajes.Service.PausaService;
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
@RequestMapping("/api/pausas")
public class PausaController {
    @Autowired
    private PausaService pausaService;

    @GetMapping("")
    public ResponseEntity<Page<PausaDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size,
                                                 @RequestParam(defaultValue = "tiempoTotalPausa, asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return new ResponseEntity<>(pausaService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PausaDTO> getById(@PathVariable Long id) {
        PausaDTO pausaDTO = pausaService.getById(id);
        if (pausaDTO != null)
            return new ResponseEntity<>(pausaDTO, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody PausaDTO pausaDTO) {
        try {
            pausaService.add(pausaDTO);
            return new ResponseEntity<>("Pausa creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error, ingresaste mal el nombre de una columna " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            pausaService.delete(id);
            return new ResponseEntity<>("Pausa eliminada exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La pausa con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PausaDTO> update(@PathVariable Long id, @RequestBody PausaDTO pausa) {
        try {
            pausaService.update(id, pausa);
            return new ResponseEntity<>(pausa, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
