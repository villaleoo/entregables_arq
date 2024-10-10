package Integrador3.Controller;

import Integrador3.DTO.CarreraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Integrador3.DTO.InscripcionDTO;
import Integrador3.DTO.ReporteDTO;
import Integrador3.Service.InscripcionService;

import java.util.List;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;


    @PostMapping("/create")
    public ResponseEntity<String> createInscrpcion(@RequestBody InscripcionDTO inscripcion) {
        inscripcionService.addInscripcion(inscripcion);
        return new ResponseEntity<>("Inscripcion creada exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/idCarrera/{idCarrera}/idEstudiante/{idEstudiante}")
    public ResponseEntity<InscripcionDTO> getInscripcionById(@PathVariable Long idCarrera, @PathVariable Long idEstudiante) {
        InscripcionDTO inscripcion = inscripcionService.getInscripcionById(idCarrera, idEstudiante);
        if (inscripcion != null)
            return new ResponseEntity<>(inscripcion, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InscripcionDTO>> getAllInscripciones() {
        return new ResponseEntity<>(inscripcionService.getAllInscripciones(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/idCarrera/{idCarrera}/idEstudiante/{idEstudiante}")
    public ResponseEntity<String> deleteInscripcion(@PathVariable Long idCarrera, @PathVariable Long idEstudiante) {
        inscripcionService.deleteInscripcion(idCarrera, idEstudiante);
        return new ResponseEntity<>("Inscripcion eliminada exitosamente", HttpStatus.OK);
    }

    @PutMapping("/update/idCarrera/{idCarrera}/idEstudiante/{idEstudiante}")
    public ResponseEntity<InscripcionDTO> updateInscripcion(@PathVariable Long idCarrera, @PathVariable Long idEstudiante, @RequestBody InscripcionDTO inscripcion) {
        inscripcionService.updateInscripcion(idCarrera, idEstudiante, inscripcion);
        return new ResponseEntity<>(inscripcion, HttpStatus.OK);
    }


    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteDTO>> getReporte() {
        return new ResponseEntity<>(inscripcionService.getReporte(), HttpStatus.OK);
    }

}
