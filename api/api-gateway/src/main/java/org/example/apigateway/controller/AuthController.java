package org.example.apigateway.controller;

import com.netflix.discovery.converters.Auto;
import org.example.apigateway.DTO.LoginDTO;
import org.example.apigateway.DTO.typeUsers.NewClientAccountDTO;
import org.example.apigateway.DTO.typeUsers.NewPersonalAccountDTO;
import org.example.apigateway.security.Constants;
import org.example.apigateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@RequestBody NewPersonalAccountDTO newAdmin){
        try{
            return this.service.createPersonalAccount(newAdmin, Constants.admin);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/mantenimiento")
    public ResponseEntity<?> createSupport(@RequestBody NewPersonalAccountDTO newSupport){
        try{
            return this.service.createPersonalAccount(newSupport,Constants.support);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<?> createClient(@RequestBody NewClientAccountDTO newSupport){
        try{
            return this.service.createClientAccount(newSupport);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login){
        try{
            return this.service.login(login);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
