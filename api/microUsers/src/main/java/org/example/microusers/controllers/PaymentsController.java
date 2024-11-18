package org.example.microusers.controllers;

import org.example.microusers.DTO.payments.NewPaymentDTO;
import org.example.microusers.DTO.payments.RechargeDTO;

import org.example.microusers.DTO.payments.DebitDetailDTO;
import org.example.microusers.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagos")
public class PaymentsController {

    @Autowired
    private PaymentsService service;


    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(this.service.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> addPayment(@RequestBody NewPaymentDTO newPayment){
        try{
            return new ResponseEntity<>(this.service.create(newPayment),HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        try{
            return new ResponseEntity<>(this.service.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/recargar/{id}")
    public ResponseEntity<?> recharge(@PathVariable Long id, @RequestBody RechargeDTO c){
        try{
            return new ResponseEntity<>(this.service.recharge(id,c),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/debitar/{id}")
    public ResponseEntity<?> debitCreditForTravel(@PathVariable UUID id, @RequestBody DebitDetailDTO c){
        try{
            return new ResponseEntity<>(this.service.debitCredit(id,c),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Error "+ e.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
