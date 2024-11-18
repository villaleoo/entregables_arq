package org.example.apigateway.service;

import org.example.apigateway.DTO.AuthResponseDTO;
import org.example.apigateway.DTO.LoginDTO;
import org.example.apigateway.DTO.account.ResponseAccountDTO;
import org.example.apigateway.DTO.externals.NewPaymentDTO;
import org.example.apigateway.DTO.typeUsers.NewClientAccountDTO;
import org.example.apigateway.DTO.typeUsers.NewPersonalAccountDTO;
import org.example.apigateway.feignClient.PaymentClient;
import org.example.apigateway.model.Cuenta;
import org.example.apigateway.model.Rol;
import org.example.apigateway.repository.AccountRepository;
import org.example.apigateway.repository.RoleRepository;
import org.example.apigateway.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository repository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    PaymentClient paymentClient;


    public ResponseEntity<?> createPersonalAccount (NewPersonalAccountDTO newAdmin, String rol){
        Optional<Cuenta> c = this.repository.findByUserName(newAdmin.getUsername());
        Optional<Rol> r =this.roleRepository.findByType(rol);

        if(c.isPresent()){
            return new ResponseEntity<>("El usuario ya existe, intenta con otro.", HttpStatus.BAD_REQUEST);
        }
        if(r.isEmpty()){
            return new ResponseEntity<>("No se econtro rol de tipo "+rol+".", HttpStatus.BAD_REQUEST);

        }

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        Cuenta account = new Cuenta();
        Rol role = r.get();
        account.setUsername(newAdmin.getUsername());
        account.setEmail(newAdmin.getEmail());
        account.setPassword(passwordEncoder.encode(newAdmin.getPassword()));
        account.setAvailable(true);
        account.setDischargeDate(Date.from(zonedDateTime.toInstant()));
        account.setRole(role);

        this.repository.save(account);


        return new ResponseEntity<>(new ResponseAccountDTO(account.getUsername(), account.getEmail()), HttpStatus.CREATED);

    }



    public ResponseEntity<?> createClientAccount(NewClientAccountDTO newClient){
        Optional<Cuenta> c = this.repository.findByUserName(newClient.getUsername());
        Optional<Rol> r =this.roleRepository.findByType("cliente");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        if(c.isPresent()){
            return new ResponseEntity<>("El nombre de usuario ya existe, intenta con otro.", HttpStatus.BAD_REQUEST);
        }

        if(r.isEmpty()){
            return new ResponseEntity<>("No se encontro rol cliente.",HttpStatus.BAD_REQUEST);
        }

        Cuenta account = new Cuenta(newClient.getUsername(),newClient.getEmail(),passwordEncoder.encode(newClient.getPassword()),
                Date.from(zonedDateTime.toInstant() ),true);
        Rol role = r.get();

        account.setRole(role);

        this.repository.save(account);

        this.paymentClient.addPayment(new NewPaymentDTO(newClient.getId_mp(),account.getId_account()));

        return new ResponseEntity<>(new ResponseAccountDTO(account.getUsername(), account.getEmail()), HttpStatus.CREATED);

    }






    public ResponseEntity<?> login(LoginDTO user){
        Optional<Cuenta> res = this.repository.findByUserName(user.getUsername());

        if(res.isEmpty()){
            return new ResponseEntity<>("El usuario no encontrado, debe registrarse para iniciar sesion.", HttpStatus.BAD_REQUEST);
        }
        Cuenta account = res.get();
        List<Rol> rol = new ArrayList<>();
        rol.add(account.getRole());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),rol.stream().map(role ->new SimpleGrantedAuthority(role.getType())).collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);

        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);

    }






}

