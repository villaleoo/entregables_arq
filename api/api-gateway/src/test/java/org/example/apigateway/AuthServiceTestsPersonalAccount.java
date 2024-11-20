package org.example.apigateway;

import org.example.apigateway.DTO.account.ResponseAccountDTO;
import org.example.apigateway.DTO.typeUsers.NewPersonalAccountDTO;
import org.example.apigateway.feignClient.PaymentClient;
import org.example.apigateway.model.Cuenta;
import org.example.apigateway.model.Rol;
import org.example.apigateway.repository.AccountRepository;
import org.example.apigateway.repository.RoleRepository;
import org.example.apigateway.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTestsPersonalAccount {

    @Mock
    AccountRepository accountRepository;
    @Mock
    RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    AuthService service;


    private NewPersonalAccountDTO newAdmin;
    private Rol role;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        newAdmin = new NewPersonalAccountDTO();
        newAdmin.setUsername("usernameFake");
        newAdmin.setEmail("thefaker@gmail.com");
        newAdmin.setPassword("themostsecurepass");


        role = new Rol();
        role.setType("admin");
    }

    @Test
    void testCrearPersonalYaExisteLaCuenta() {
        when(accountRepository.findByUserName(newAdmin.getUsername())).thenReturn(Optional.of(new Cuenta()));

        ResponseEntity<?> response = service.createPersonalAccount(newAdmin, "admin");

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El usuario ya existe, intenta con otro.", response.getBody());

        //verifica que nunca se haya hecho un save en el repositorio
        verify(accountRepository, never()).save(any());
    }


    @Test
    void testCrearPersonalNoHayRol() {
        when(accountRepository.findByUserName(newAdmin.getUsername())).thenReturn(Optional.empty());

        when(roleRepository.findByType("admin")).thenReturn(Optional.empty());

        ResponseEntity<?> response = service.createPersonalAccount(newAdmin, "admin");

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("No se econtro rol de tipo admin.", response.getBody());

        verify(accountRepository, never()).save(any());
    }

    @Test
    void testCrearPersonalExitoso() {
        when(accountRepository.findByUserName(newAdmin.getUsername())).thenReturn(Optional.empty());

        when(roleRepository.findByType("admin")).thenReturn(Optional.of(role));

        when(passwordEncoder.encode(newAdmin.getPassword())).thenReturn("passCodificada");

        ResponseEntity<?> response = service.createPersonalAccount(newAdmin, "admin");

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ResponseAccountDTO responseBody = (ResponseAccountDTO) response.getBody();
        assertNotNull(responseBody);
        assertEquals(newAdmin.getUsername(), responseBody.getUsername());
        assertEquals(newAdmin.getEmail(), responseBody.getEmail());

        verify(accountRepository).save(any(Cuenta.class));
    }
}
