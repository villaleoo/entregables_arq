package org.example.microgestionparadas.controllers;

import org.example.microgestionparadas.DTO.StopDTO;
import org.example.microgestionparadas.services.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paradas")
public class StopController {

    @Autowired
    StopService service;

    @PostMapping("")
    public ResponseEntity<?> createStop(@RequestBody StopDTO stop){
        try{
            return new ResponseEntity<>(this.service.save(stop), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStop(@PathVariable Long id, @RequestBody StopDTO stop){
        try{
            return new ResponseEntity<>(this.service.update(id,stop), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStop(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.delete(id), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
