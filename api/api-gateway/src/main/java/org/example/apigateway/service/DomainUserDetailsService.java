package org.example.apigateway.service;


import jakarta.transaction.Transactional;
import org.example.apigateway.model.Cuenta;
import org.example.apigateway.model.Rol;
import org.example.apigateway.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final AccountRepository userRepository;

    public DomainUserDetailsService( AccountRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) {
        Optional<Cuenta> res = this.userRepository.findByUserName(username.toLowerCase());

        if(res.isPresent()){
            Cuenta c = res.get();
            List<Rol> rol = new ArrayList<>();
            rol.add(c.getRole());

            return new User(c.getUsername(),c.getPassword(),rol.stream().map(role ->new SimpleGrantedAuthority(role.getType())).collect(Collectors.toList()));

        }
        throw new RuntimeException("Usuario no encontrado.") ;
    }



}

