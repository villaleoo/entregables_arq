package org.example.microgestion.controllers;

import org.example.microgestion.DTO.InitTravelDTO;
import org.example.microgestion.DTO.externals.NewKmsMonoDTO;
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

    @PostMapping("")
    public ResponseEntity<?> initTravel(@RequestBody InitTravelDTO travel){
        try{
            return new ResponseEntity<>(this.service.initTravel(travel), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(this.service.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/informe/monopatines")
    public ResponseEntity<?> getAllMonoWithTravels(@RequestParam Integer anio,@RequestParam Integer minViaje){
        try{
            return new ResponseEntity<>(this.service.findListMonopatines(anio,minViaje),HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
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

    @GetMapping("/en-curso/monopatin/{id}")
    public ResponseEntity<?> getIdTravelInProgressOfMonopatin(@PathVariable String id){
        try{
            return new ResponseEntity<>(this.service.findTravelIdActiveOfMono(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/debitar")
    ResponseEntity<?> updateKmsAndDebitMoney(@PathVariable Long id, @RequestBody NewKmsMonoDTO kms){
        try{
            return new ResponseEntity<>(this.service.updateKmsAndDebitMoney(id,kms),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}

