package org.example.microusers.controllers;

import org.example.microusers.DTO.AccountDTO;
import org.example.microusers.DTO.ResponseDebitDTO;
import org.example.microusers.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cuentas")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO c){
        try{
            return new ResponseEntity<>(this.service.save(c),HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(this.service.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountDTO c){
        try{
            return new ResponseEntity<>(this.service.update(id,c),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("{id}/inhabilitar")
    public ResponseEntity<?> updateAccount(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.disable(id),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/debitar")
    public ResponseEntity<?> debitCreditForTravel(@PathVariable Long id, @RequestBody ResponseDebitDTO c){
        try{
            return new ResponseEntity<>(this.service.debitCredit(id,c),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
