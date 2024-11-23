package com.gateway.api_gateway.Service;

import com.gateway.api_gateway.Dto.user.UserDTO;
import com.gateway.api_gateway.Entity.User;
import com.gateway.api_gateway.Repository.AuthorityRepository;
import com.gateway.api_gateway.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public long saveUser( UserDTO request ) {
        final var user = new User( request.getUsername() );
        user.setPassword( passwordEncoder.encode( request.getPassword() ) );
        final var roles =  this.authorityRepository.findAllById( request.getAuthorities() );
        user.setAuthorities( roles );
        final var result = this.userRepository.save( user );
        return result.getId();
    }
}
