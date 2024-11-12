package org.example.microusers.services;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.microusers.DTO.NewPersonalCompanyDTO;
import org.example.microusers.DTO.ClientWithAccountDTO;
import org.example.microusers.DTO.UserVisibleDataDTO;
import org.example.microusers.DTO.UserDTO;
import org.example.microusers.model.*;
import org.example.microusers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    AccountRepository repositoryAccount;
    @Autowired
    UserRepository repositoryUser;
    @Autowired
    RoleRepository repositoryRole;


    public List<Usuario> findAllProtected() {
        return this.repositoryUser.findAll();
    }

    public List<Usuario> findAllUsers(){
        return this.repositoryUser.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> res = this.repositoryUser.findById(id);

        if(res.isPresent()){
            return res.get();
        }

        throw new EntityNotFoundException("No se pudo encontrar usuario valido con id: "+id);

    }



    public UserDTO saveClient(ClientWithAccountDTO entity) {
        Optional<Cuenta> c = this.repositoryAccount.findById(entity.getId_account());
        System.out.println(this.repositoryAccount.findById(entity.getId_account()));
        Optional<Rol> r = this.repositoryRole.findByType("cliente");

        if(c.isPresent() && r.isPresent()){
            Rol role = r.get();
            Cuenta account = c.get();
            Usuario user = new Usuario(entity.getName(), entity.getSurname(), entity.getPhone(), entity.getEmail(),entity.getRegistrationDate(),role,null);
            user.setAccount(account);
            this.repositoryUser.save(user);

            return new UserDTO(entity.getName(), entity.getSurname(), entity.getPhone(), entity.getEmail(), entity.getRegistrationDate(),role.getType());
        }

        if(!c.isPresent()){
            throw new EntityNotFoundException("La cuenta vinculada al nuevo usuario no existe.");
        }

        throw new EntityNotFoundException("Rol de usuario no encontrado.");
    }

    @Transactional
    public UserDTO saveAdmin(NewPersonalCompanyDTO entity){
        Optional<Rol> r = this.repositoryRole.findByType("admin");

        if(r.isPresent()){
            Rol role = r.get();
            Usuario user = new Usuario(entity.getName(), entity.getSurname(), entity.getPhone(), entity.getEmail(),entity.getRegistrationDate(),role,null);

            this.repositoryUser.save(user);

            return new UserDTO(entity.getName(), entity.getSurname(), entity.getPhone(), entity.getEmail(), entity.getRegistrationDate(),role.getType());
        }

        throw new EntityNotFoundException("Rol de usuario no encontrado.");
    }

    @Transactional
    public UserDTO saveSupport(NewPersonalCompanyDTO entity){
        Optional<Rol> r = this.repositoryRole.findByType("mantenimiento");

        if(r.isPresent()){
            Rol role = r.get();
            Usuario user = new Usuario(entity.getName(), entity.getSurname(), entity.getPhone(), entity.getEmail(),entity.getRegistrationDate(),role,null);

            this.repositoryUser.save(user);

            return new UserDTO(entity.getName(), entity.getSurname(), entity.getPhone(), entity.getEmail(), entity.getRegistrationDate(),role.getType());
        }

        throw new EntityNotFoundException("Rol de usuario no encontrado.");
    }


    public UserVisibleDataDTO update(Long id, UserVisibleDataDTO entity){
        Optional<Usuario> u = this.repositoryUser.findById(id);

        if(u.isPresent()){
            Usuario user = u.get();
            user.setEmail(entity.getEmail());
            user.setName(entity.getName());
            user.setSurname(entity.getSurname());
            user.setPhone(entity.getPhone());
            this.repositoryUser.save(user);

            return entity;
        }

        throw new EntityNotFoundException("No se encontro usuario con id: "+id);
    }


    public UserVisibleDataDTO delete(Long id) throws Exception {
        Optional<Usuario> u = this.repositoryUser.findById(id);

        if(u.isPresent()){
            Usuario user = u.get();
            this.repositoryUser.delete(user);

            return new UserVisibleDataDTO(user.getName(), user.getSurname(), user.getPhone(), user.getEmail());
        }

        throw new EntityNotFoundException("No se encontro usuario con id: "+id);
    }
}
