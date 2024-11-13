package org.example.microusers.controllers;

import org.example.microusers.DTO.clientAccount.ClientAccountDTO;
import org.example.microusers.DTO.clientAccount.RechargeDTO;
import org.example.microusers.DTO.clientAccount.UpdateAccountDTO;
import org.example.microusers.DTO.payments.DebitDetailDTO;
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
    public ResponseEntity<?> createAccount(@RequestBody ClientAccountDTO c){
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

    @PutMapping("{id}/inhabilitar")
    public ResponseEntity<?> disableAccount(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.disable(id),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody UpdateAccountDTO c){
        try{
            return new ResponseEntity<>(this.service.update(id,c),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/recargar")
    public ResponseEntity<?> rechargeAccount(@PathVariable Long id, @RequestBody RechargeDTO c){
        try{
            return new ResponseEntity<>(this.service.recharge(id,c),HttpStatus.OK);
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


    @PutMapping("/{id}/debitar")
    public ResponseEntity<?> debitCreditForTravel(@PathVariable Long id, @RequestBody DebitDetailDTO c){
        try{
            return new ResponseEntity<>(this.service.debitCredit(id,c),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
