package org.example.microusers.services;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.microusers.DTO.AccountDTO;
import org.example.microusers.DTO.user.UserDTO;
import org.example.microusers.DTO.user.UserVisibleDataDTO;
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



    public UserDTO saveClient(NewUserDTO entity) throws IOException {
        Long idAccount=this.getAccount(entity.getId_account());
        /*falta validar que ante un nuevo usuario verificar si ya existe el usuario vinculado a esa cuenta findClientInAcc(emailNuevoUsuario, idCuenta)*/
        /*si el nuevo usuario no esta vinculado a la cuenta, lo agrego, sino le indico y muesto datos de cuenta//usuario*/
        /*falta validar que la cuenta que encuentra tiene que ser de tipo CLIENTE*/
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));


        if(idAccount!= null){
            Usuario user = new Usuario(entity.getName().toLowerCase(), entity.getSurname().toLowerCase(),
                    entity.getPhone(), entity.getEmail().toLowerCase(), Date.from(zonedDateTime.toInstant()));
            user.setId_account(idAccount);
            this.repositoryUser.save(user);

            return new UserDTO(user.getId_user(),entity.getName(), entity.getSurname(), entity.getPhone(), entity.getEmail(), Date.from(zonedDateTime.toInstant()));
        }

        throw new EntityNotFoundException("La cuenta vinculada al nuevo usuario no existe.");

    }

    public UserVisibleDataDTO update(Long id, UserVisibleDataDTO entity){
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


    public UserVisibleDataDTO delete(Long id) {
        Optional<Usuario> u = this.repositoryUser.findById(id);

        if(u.isPresent()){
            Usuario user = u.get();
            this.repositoryUser.delete(user);

            return new UserVisibleDataDTO(user.getName(), user.getSurname(), user.getPhone(), user.getEmail());
        }

        throw new EntityNotFoundException("No se encontro usuario con id: "+id);
    }

    private Long getAccount(Long id) {
        ResponseEntity<?> res = this.authClient.getById(id);

        if(res.getStatusCode().is2xxSuccessful() && res.getBody()!=null){
            System.out.println(res.getBody());
            Map<String, Object> body = (Map<String, Object>) res.getBody();


            body.remove("role");

            ObjectMapper objectMapper = new ObjectMapper();
            AccountDTO c = objectMapper.convertValue(body, AccountDTO.class);

            return c.getId_account();
        }

        return null;
    }
}
