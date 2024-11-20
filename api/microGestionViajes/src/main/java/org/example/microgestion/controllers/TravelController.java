package org.example.microgestion.controllers;

import jakarta.validation.Valid;
import org.example.microgestion.DTO.travels.InitTravelDTO;
import org.example.microgestion.DTO.externals.monopatin.KmsOnMonoTravelingDTO;
import org.example.microgestion.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viajes")
public class TravelController {

    @Autowired
    TravelService service;

    @PostMapping("/")
    public ResponseEntity<?> initTravel(@Valid @RequestBody InitTravelDTO travel){
        try{
            return new ResponseEntity<>(this.service.initTravel(travel), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(this.service.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/informe/facturado")
    public ResponseEntity<?> getTotalBilledPerMonth(@RequestParam Integer mesInicial, @RequestParam Integer mesFinal, @RequestParam(required = false) Integer anio){
        try{
            return new ResponseEntity<>(this.service.findTotalBilled(mesInicial,mesFinal,anio),HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> endTravel(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.endTravel(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/pausar/{id}")
    public ResponseEntity<?> pauseTravel(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.pauseTravel(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/reanudar/{id}")
    public ResponseEntity<?> unPauseTravel(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.unPauseTravel(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }




    /*endpoint desarrollado para el microservicio de monopatines*/
    @GetMapping("/en-curso/monopatin/{id}")
    public ResponseEntity<?> getIdTravelInProgressOfMonopatin(@PathVariable String id){
        try{
            return new ResponseEntity<>(this.service.findTravelIdActiveOfMono(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /*endpoint desarrollado para el microservicio de monopatines*/
    @GetMapping("/acumulado")
    public ResponseEntity<?> getDetailsTravelsMonopatin(@RequestParam String monopatin){
        try{
            return new ResponseEntity<>(this.service.findTravelsDetailsOfMono(monopatin),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /*endpoint desarrollado para el microservicio de monopatines*/
    @GetMapping("/informe/monopatines")
    public ResponseEntity<?> getAllMonoWithTravels(@RequestParam Integer anio,@RequestParam Integer minViaje){
        try{
            return new ResponseEntity<>(this.service.findListMonopatines(anio,minViaje),HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }


}

