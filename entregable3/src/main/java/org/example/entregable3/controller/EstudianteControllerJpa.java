package org.example.entregable3.controller;


import jakarta.persistence.EntityNotFoundException;
import org.example.entregable3.DTO.EstudianteDTO;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.service.EstudianteService;
import org.example.entregable3.utils.enums.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estudiantes")
public class EstudianteControllerJpa {


    @Autowired
    private EstudianteService service;



    @PostMapping("")
    public ResponseEntity<?> createEstudiante(@RequestBody EstudianteDTO estudiante) {
        try{
            return new ResponseEntity<>(this.service.save(estudiante), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Error." + e.getMessage(), HttpStatus.CONFLICT);

        }

    }

    @GetMapping("/")
    public ResponseEntity<?> getEstudiantes() throws Exception {
        try{
            return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
        }catch(Exception e){
            throw new Exception(e.getMessage());

        }

    }

    @GetMapping("/apellido_alfabetico")
    public  ResponseEntity<?>  getAlphabetic () throws Exception {
        try{
            return  new ResponseEntity<>(this.service.findAllAlphabeticSurname(), HttpStatus.OK);
        }catch(Exception e){
            throw new Exception(e.getMessage());

        }
    }

    @GetMapping("/{num_libreta}")
    public ResponseEntity<?> getByNumLibreta(@PathVariable String num_libreta) {
        try{
            return  new ResponseEntity<>(this.service.findByEnrolled(num_libreta), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>("No existe estudiante con idlibreta: " + num_libreta , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> getAllByGenero(@PathVariable Genero genero) throws Exception {
        try{
            return  new ResponseEntity<>(this.service.findAllByGenero(genero), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>("No hay estudiantes de genero "+genero, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/porCiudadCarrera")
    public ResponseEntity<?> getAllByCarreraAndCity(@RequestParam String ciudad, @RequestParam Integer id_carrera) throws Exception {
        try{
            return  new ResponseEntity<>(this.service.findAllByCiudadAndCarrera(ciudad,id_carrera), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>("No hay estudiantes de  "+ciudad+" y de carrera "+id_carrera, HttpStatus.NOT_FOUND);
        }
    }



}
