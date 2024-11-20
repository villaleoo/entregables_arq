package org.example.micromonopatines.controllers;


import jakarta.validation.Valid;
import org.example.micromonopatines.DTO.location.LocationDTO;
import org.example.micromonopatines.DTO.MonoDTO;
import org.example.micromonopatines.DTO.UpdateMonoDTO;
import org.example.micromonopatines.services.MonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monopatines")
public class MonoController {

    @Autowired
    MonoService service;

    @PostMapping("/")
    public ResponseEntity<?> createMono(@Valid @RequestBody MonoDTO mono){
        try{
            return  new ResponseEntity<>(this.service.save(mono),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try{
            return  new ResponseEntity<>(this.service.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        try{
            return  new ResponseEntity<>(this.service.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMono(@PathVariable String id,@Valid @RequestBody UpdateMonoDTO mono){
        try{
            return  new ResponseEntity<>(this.service.update(id,mono),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMono(@PathVariable String id){
        try{
            return  new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/mas-viajes")
    public ResponseEntity<?> getOrderByTravelAndYear(@RequestParam Integer anio, @RequestParam Integer minViaje){
        try{
            return  new ResponseEntity<>(this.service.findByTravelAndYear(anio,minViaje),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<?> getAvailableReport(){
        try{
            return new ResponseEntity<>(this.service.findAvailableReport(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /*este endpoint fue creado para el microservicio de paradas, no se muestra en el uso de la API*/
    @GetMapping("/en-parada")
    public ResponseEntity<?> getAllInStop(@RequestParam Long idParada){
        try{
            return new ResponseEntity<>(this.service.findAvailableInStop(idParada),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/utilizacion/{id}")
    public ResponseEntity<?> getUsageReport(@PathVariable String id,@RequestParam(required = false) Boolean conPausas){
        try{
            return new ResponseEntity<>(this.service.generateUsageReport(id,conPausas),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/ubicacion/{id}")
    public ResponseEntity<?> updateLocationInTravel(@PathVariable String id, @Valid @RequestBody LocationDTO location){
        try{
            return  new ResponseEntity<>(this.service.updateLocationInTravel(id,location),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/disponibilidad/{id}")
    public ResponseEntity<?> updateAvailable(@PathVariable String id){
        try{
            return  new ResponseEntity<>(this.service.updateAvailable(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
