package org.example.apigateway;

import org.example.apigateway.DTO.AuthResponseDTO;
import org.example.apigateway.DTO.LoginDTO;
import org.example.apigateway.feignClient.PaymentClient;
import org.example.apigateway.model.Cuenta;
import org.example.apigateway.model.Rol;
import org.example.apigateway.repository.AccountRepository;
import org.example.apigateway.repository.RoleRepository;
import org.example.apigateway.security.JwtProvider;
import org.example.apigateway.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTestLogin {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthService service;


    @Test
    void testLoginUsuarioNoEncontrado() {
        LoginDTO user = new LoginDTO("testUser", "testPassword");

        when(accountRepository.findByUserName(user.getUsername())).thenReturn(Optional.empty());

        ResponseEntity<?> response = service.login(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El usuario no encontrado, debe registrarse para iniciar sesion.", response.getBody());

        verify(jwtProvider, never()).createToken(any());
        verify(accountRepository).findByUserName(user.getUsername());
    }

    @Test
    void testLoginExitoso() {
        LoginDTO user = new LoginDTO("testUser", "testPassword");
        Rol rol = new Rol("cliente");
        Cuenta account = new Cuenta();
        account.setUsername("testUser");
        account.setPassword("hashedPassword");
        account.setRole(rol);

        when(accountRepository.findByUserName(user.getUsername())).thenReturn(Optional.of(account));
        when(jwtProvider.createToken(any(Authentication.class))).thenReturn("fake-jwt-token");

        ResponseEntity<?> response = service.login(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(AuthResponseDTO.class, response.getBody());
        AuthResponseDTO responseBody = (AuthResponseDTO) response.getBody();
        assertEquals("fake-jwt-token", responseBody.getToken());

        verify(accountRepository).findByUserName(user.getUsername());
        verify(jwtProvider).createToken(any(Authentication.class));
    }


    @Test
    void testLoginPasswordIncorrect() {
        LoginDTO user = new LoginDTO("testUser", "fakepass");
        Rol rol = new Rol("cliente");
        Cuenta account = new Cuenta();
        account.setUsername("testUser");
        account.setPassword(passwordEncoder.encode("correctpasss"));
        account.setRole(rol);

        when(accountRepository.findByUserName(user.getUsername())).thenReturn(Optional.of(account));

        ResponseEntity<?> response = service.login(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Contraseña incorrecta.", response.getBody());

        verify(accountRepository).findByUserName(user.getUsername());
        verify(jwtProvider, never()).createToken(any());
    }
}
