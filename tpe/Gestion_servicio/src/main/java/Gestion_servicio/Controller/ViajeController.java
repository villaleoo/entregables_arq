package Gestion_servicio.Controller;

import Gestion_servicio.Dto.MonopatinDTO;
import Gestion_servicio.Dto.RequestDTO;
import Gestion_servicio.Dto.ViajeDTO;
import Gestion_servicio.Entity.Viaje;
import Gestion_servicio.Service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {
    @Autowired
    private ViajeService service;

    @GetMapping("")
    public ResponseEntity<List<ViajeDTO>> traerViajes() {
        return ResponseEntity.ok(service.traerViajes());
    }
    @GetMapping("/monopatines")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinesPorAnio(@ModelAttribute RequestDTO request){
        return ResponseEntity.ok(service.monopatinesPorAnio(request.getViajes(), request.getAnio()));
    }
    @GetMapping("/facturado")
    public ResponseEntity<String> totalFacturadoEnAnioEntreMeses(RequestDTO req) {
        try{
            Integer total = service.totalFacturadoEnAnioEntreMeses(req.getAnio(), req.getInicio(), req.getFin());
            return new ResponseEntity<>("En el año "+req.getAnio()+" se facturo un total de "+total
                    +" entre el mes "+req.getInicio()+" y "+req.getFin(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Algo salio mal.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/admin/cantmonopatines")
    public ResponseEntity<String> getCantMonopatines() {
        //Como administrador quiero consultar la cantidad de monopatines actualmente en operación,
        //versus la cantidad de monopatines actualmente en mantenimiento.
        try{
            String cantidades = service.getCantMonopatines();
            return new ResponseEntity<>(cantidades, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Algo salio mal.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> nuevoViaje(@RequestBody Viaje v){
        try{
            service.nuevoViaje(v);
            return new ResponseEntity<>("Se agrego un nuevo viaje!", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Algo salio mal.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("(/{id}/fin?t={tiempo}+km={km}")
    public ResponseEntity<String> finViaje(@PathVariable Integer id, @PathVariable Integer tiempo,
                                           @PathVariable Integer km){
        try{
            service.finViaje(id, tiempo, km);
            return new ResponseEntity<>("Se ha finalizado el viaje viaje!", HttpStatus.ACCEPTED);
        } catch(Exception e) {
            return new ResponseEntity<>("Algo salio mal.", HttpStatus.BAD_REQUEST);
        }
    }
}
