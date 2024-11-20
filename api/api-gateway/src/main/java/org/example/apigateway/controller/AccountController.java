package org.example.apigateway.controller;


import jakarta.validation.Valid;
import org.example.apigateway.DTO.account.RechargeDTO;
import org.example.apigateway.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    AccountService service;


    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(this.service.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }



    @PutMapping("/inhabilitar/{id}")
    ResponseEntity<?> disable(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.disable(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/recargar/{id}")
    ResponseEntity<?> recharge(@PathVariable Long id, @Valid @RequestBody RechargeDTO recharge){
        try{
            return new ResponseEntity<>(this.service.recharge(id,recharge),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }





}
