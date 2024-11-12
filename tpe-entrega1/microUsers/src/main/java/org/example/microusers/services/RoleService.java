package org.example.microusers.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microusers.DTO.RoleDTO;
import org.example.microusers.model.Rol;
import org.example.microusers.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    @Autowired
    RoleRepository repository;

    public RoleDTO save(RoleDTO role){
        this.repository.save(new Rol(role.getType()));
        return role;
    }

}
