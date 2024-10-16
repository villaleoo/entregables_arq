package org.example.entregable3.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.entregable3.DTO.InscripcionDTO;
import org.example.entregable3.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inscripciones")
public class InscripcionControllerJpa {

    @Autowired
    private InscripcionService service;



    @PostMapping("")
    public ResponseEntity<?> createInscripcion(@RequestBody InscripcionDTO inscripcion) {
        try {
            return new ResponseEntity<>( this.service.save(inscripcion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al insertar la inscripción.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return  new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return  new ResponseEntity<>("No se encontraron inscripciones.", HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/carreras")
    public ResponseEntity<?> getReportCarreras (){
        try{
            return  new ResponseEntity<>(this.service.getReport(), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return  new ResponseEntity<>("No se encontraron reportes.", HttpStatus.NOT_FOUND);
        }

    }
}
