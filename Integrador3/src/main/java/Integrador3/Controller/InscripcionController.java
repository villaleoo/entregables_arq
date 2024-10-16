package Integrador3.Controller;

import Integrador3.DTO.CarreraDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Integrador3.DTO.InscripcionDTO;
import Integrador3.DTO.ReporteDTO;
import Integrador3.Service.InscripcionService;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;


    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody InscripcionDTO inscripcion) {
        try {
            inscripcionService.add(inscripcion);
            return new ResponseEntity<>("Inscripcion creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al insertar la inscripción con idCarrera: " +
                    inscripcion.getIdCarrera() + " y estudiante: " + inscripcion.getDocumentoEstudiante(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/idCarrera/{idCarrera}/dniEstudiante/{idEstudiante}")
    public ResponseEntity<InscripcionDTO> getById(@PathVariable Long idCarrera, @PathVariable Long idEstudiante) {
        InscripcionDTO inscripcion = inscripcionService.getById(idCarrera, idEstudiante);
        if (inscripcion != null)
            return new ResponseEntity<>(inscripcion, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<List<InscripcionDTO>> getAll() {
        return new ResponseEntity<>(inscripcionService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/idCarrera/{idCarrera}/dniEstudiante/{idEstudiante}")
    public ResponseEntity<String> delete(@PathVariable Long idCarrera, @PathVariable Long idEstudiante) {
        try {
            inscripcionService.delete(idCarrera, idEstudiante);
            return new ResponseEntity<>("Inscripcion eliminada exitosamente", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Inscripcion no encontrada con idCarrera: " + idCarrera + " , idEstudiante:" + idEstudiante, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/idCarrera/{idCarrera}/dniEstudiante/{idEstudiante}")
    public ResponseEntity<InscripcionDTO> update(@PathVariable Long idCarrera, @PathVariable Long idEstudiante, @RequestBody InscripcionDTO inscripcion) {
        try {
            inscripcionService.update(idCarrera, idEstudiante, inscripcion);
            return new ResponseEntity<>(inscripcion, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteDTO>> getReporte() {
        return new ResponseEntity<>(inscripcionService.getReporte(), HttpStatus.OK);
    }

}
