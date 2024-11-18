package org.example.microusers.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import jakarta.persistence.EntityNotFoundException;
import org.example.microusers.DTO.AccountDTO;
import org.example.microusers.DTO.user.UserDTO;
import org.example.microusers.DTO.user.ClientVisibleDataDTO;
import org.example.microusers.DTO.user.NewUserDTO;
import org.example.microusers.feignClient.AuthClient;
import org.example.microusers.model.*;
import org.example.microusers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserRepository repositoryUser;
    @Autowired
    AuthClient authClient;


    public List<Usuario> findAll() {
        return this.repositoryUser.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> res = this.repositoryUser.findById(id);

        if(res.isPresent()){
            return res.get();
        }

        throw new EntityNotFoundException("No se pudo encontrar usuario valido con id: "+id);
    }



    public UserDTO saveClient(NewUserDTO entity) {
        Long idAccount=this.getAccount(entity.getId_account());
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));


        if(idAccount!= null){
            Usuario user = new Usuario(entity.getName().toLowerCase(), entity.getSurname().toLowerCase(),
                    entity.getPhone(), entity.getEmail().toLowerCase(), Date.from(zonedDateTime.toInstant()));
            user.setId_account(idAccount);
            this.repositoryUser.save(user);

            return new UserDTO(entity.getName(), entity.getSurname(), entity.getPhone(), entity.getEmail(), Date.from(zonedDateTime.toInstant()));
        }

        throw new EntityNotFoundException("La cuenta vinculada al nuevo usuario no existe.");

    }

    public ClientVisibleDataDTO update(Long id, ClientVisibleDataDTO entity){
        Optional<Usuario> u = this.repositoryUser.findById(id);

        if(u.isPresent()){
            Usuario user = u.get();
            user.setEmail(entity.getEmail().toLowerCase());
            user.setName(entity.getName().toLowerCase());
            user.setSurname(entity.getSurname().toLowerCase());
            user.setPhone(entity.getPhone());
            this.repositoryUser.save(user);

            return entity;
        }

        throw new EntityNotFoundException("No se encontro usuario con id: "+id);
    }


    public ClientVisibleDataDTO delete(Long id) {
        Optional<Usuario> u = this.repositoryUser.findById(id);

        if(u.isPresent()){
            Usuario user = u.get();
            this.repositoryUser.delete(user);

            return new ClientVisibleDataDTO(user.getName(), user.getSurname(), user.getPhone(), user.getEmail());
        }

        throw new EntityNotFoundException("No se encontro usuario con id: "+id);
    }

    private Long getAccount(Long id){
        ResponseEntity<?> res = this.authClient.findById(id);

        if(res.getStatusCode().is2xxSuccessful() && res.getBody()!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            AccountDTO c = objectMapper.convertValue(res.getBody(), AccountDTO.class);
            return c.getId_account();
        }

        return null;
    }
}
