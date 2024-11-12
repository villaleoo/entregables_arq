package org.example.microusers.controllers;

import org.example.microusers.DTO.NewPersonalCompanyDTO;
import org.example.microusers.DTO.ClientWithAccountDTO;
import org.example.microusers.DTO.UserVisibleDataDTO;
import org.example.microusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody ClientWithAccountDTO newClient){
        try{
            return new ResponseEntity<>(this.service.saveClient(newClient), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@RequestBody NewPersonalCompanyDTO newAdmin){
        try{
            return new ResponseEntity<>(this.service.saveAdmin(newAdmin), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/mantenimiento")
    public ResponseEntity<?> createSupport(@RequestBody NewPersonalCompanyDTO newSupport){
        try{
            return new ResponseEntity<>(this.service.saveSupport(newSupport), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(this.service.findAllProtected(), HttpStatus.OK);
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
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserVisibleDataDTO user){
        try{
            return new ResponseEntity<>(this.service.update(id,user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }


}
