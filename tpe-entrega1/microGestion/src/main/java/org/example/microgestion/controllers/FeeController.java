package org.example.microgestion.controllers;

import org.example.microgestion.DTO.fees.FeeDTO;
import org.example.microgestion.services.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarifas")
public class FeeController {

    @Autowired
    FeeService service;

    @PostMapping("")
    public ResponseEntity<?> createFee(@RequestBody FeeDTO fee){
        try{
            return new ResponseEntity<>(this.service.save(fee), HttpStatus.CREATED);
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFee(@PathVariable Long id, @RequestBody FeeDTO fee){
        try{
            return new ResponseEntity<>(this.service.update(id,fee),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFee(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}