package org.example.microusers.controllers;

import org.example.microusers.DTO.RoleDTO;
import org.example.microusers.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RoleController {

    @Autowired
    RoleService service;

    @PostMapping("")
    public ResponseEntity<?> createRole(@RequestBody RoleDTO type){
        try{
            return new ResponseEntity<>(this.service.save(type), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
