package MicroservicioMonopatin.Controller;

import MicroservicioMonopatin.DTO.MonopatinDTO;
import MicroservicioMonopatin.DTO.ParadaDTO;
import MicroservicioMonopatin.Service.ParadaService;
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
@RequestMapping("/api/paradas")
public class ParadaController {
    @Autowired
    private ParadaService paradaService;

    @GetMapping("")
    public ResponseEntity<Page<ParadaDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size,
                                                  @RequestParam(defaultValue = "id, asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return new ResponseEntity<>(paradaService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParadaDTO> getById(@PathVariable Long id) {
        ParadaDTO parada = paradaService.getById(id);
        if (parada != null)
            return new ResponseEntity<>(parada, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody ParadaDTO parada) {
        try {
            paradaService.add(parada);
            return new ResponseEntity<>("Parada creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error, ingresaste mal el nombre de una columna " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            paradaService.delete(id);
            return new ResponseEntity<>("Parada eliminada exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La parada con id: " + id + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParadaDTO> update(@PathVariable Long id, @RequestBody ParadaDTO parada) {
        try {
            paradaService.update(id, parada);
            return new ResponseEntity<>(parada, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cercanas")
    public ResponseEntity<Page<ParadaDTO>> getParadasCercanas(@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer size,
                                                              @RequestParam int x,
                                                              @RequestParam int y,
                                                              String[] sort) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(paradaService.getParadasCercanas(pageable, x, y), HttpStatus.OK);
    }

    @PutMapping("/agregarMonopatin/{id}")
    public ResponseEntity<Void> agregarMonopatinaParada(@PathVariable Long id, @RequestBody MonopatinDTO monopatinDTO) {
        paradaService.agregarMonopatinaParada(id, monopatinDTO);
        return ResponseEntity.ok().build();
    }
}
