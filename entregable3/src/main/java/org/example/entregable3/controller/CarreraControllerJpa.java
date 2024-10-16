package org.example.entregable3.controller;


import jakarta.persistence.EntityNotFoundException;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carreras")
public class CarreraControllerJpa {

    @Autowired
    private CarreraService service;

    @GetMapping("/")
    public ResponseEntity<?> getAll (){
        try{
            return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("No se encontraron resultados", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/inscriptos")
    public ResponseEntity<?> getAllMostEnrolled (){
        try{
            return  new ResponseEntity<>(this.service.findAllByEnrolled(), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return  new ResponseEntity<>("No se encontraron resultados", HttpStatus.NOT_FOUND);
        }

    }


}
