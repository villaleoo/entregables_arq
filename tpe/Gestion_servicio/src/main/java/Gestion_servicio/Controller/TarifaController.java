package Gestion_servicio.Controller;

import Gestion_servicio.Entity.Tarifa;
import Gestion_servicio.Service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarifas")
public class TarifaController {
    @Autowired
    private TarifaService service;

    @PostMapping("")
    public ResponseEntity<String> nuevo(@RequestBody Tarifa t) {
        try {
            service.nuevaTarifa(t);
            return new ResponseEntity<>("Tarifa agregada.", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("No se pudo agregar la tarifa.", HttpStatus.BAD_REQUEST);
        }
    }
    //Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema
    //habilite los nuevos precios.
}
