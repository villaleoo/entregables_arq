package org.example.apigateway;

import org.example.apigateway.DTO.account.ResponseAccountDTO;
import org.example.apigateway.DTO.externals.NewPaymentDTO;
import org.example.apigateway.DTO.typeUsers.NewClientAccountDTO;
import org.example.apigateway.DTO.typeUsers.NewPersonalAccountDTO;
import org.example.apigateway.feignClient.PaymentClient;
import org.example.apigateway.model.Cuenta;
import org.example.apigateway.model.Rol;
import org.example.apigateway.repository.AccountRepository;
import org.example.apigateway.repository.RoleRepository;
import org.example.apigateway.service.AccountService;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTestClientAccount {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService service;

    private NewClientAccountDTO newClient;
    private Rol role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        newClient = new NewClientAccountDTO();
        newClient.setUsername("pepe");
        newClient.setEmail("pepecliente@gimal.com");
        newClient.setPassword("passfake111");
        newClient.setId_mp("550e8400-e29b-41d4-a716-446655440000");

        role = new Rol();
        role.setType("cliente");
    }

    @Test
    void testCrearCuentaClienteYaExiste() {
        when(accountRepository.findByUserName(newClient.getUsername())).thenReturn(Optional.of(new Cuenta()));

        ResponseEntity<?> response = service.createClientAccount(newClient);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El nombre de usuario ya existe, intenta con otro.", response.getBody());

        verify(accountRepository, never()).save(any());
        verify(paymentClient, never()).addPayment(any());
    }

    @Test
    void testCrearCuentaClienteIdMpInvalido() {
        newClient.setId_mp("invalidoFake");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.createClientAccount(newClient);
        });

        assertEquals("El id de la cuenta de mercado pago no es valida. Debe ser formato UUID xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxx.", exception.getMessage());

        verify(accountRepository, never()).save(any());
        verify(paymentClient, never()).addPayment(any());
    }

    @Test
    void testCreateClientAccount_Success() {
        when(accountRepository.findByUserName(newClient.getUsername())).thenReturn(Optional.empty());

        Rol mockRole = new Rol();
        mockRole.setType("cliente");
        when(roleRepository.findByType("cliente")).thenReturn(Optional.of(mockRole));

        Cuenta mockAccount = new Cuenta();
        mockAccount.setId_account((Long) 1L);
        mockAccount.setUsername(newClient.getUsername());
        mockAccount.setEmail(newClient.getEmail());
        when(accountRepository.save(any(Cuenta.class))).thenReturn(mockAccount);


        when(paymentClient.addPayment(any(NewPaymentDTO.class)))
                .thenReturn(ResponseEntity.ok().build());



        ResponseEntity<?> response = service.createClientAccount(newClient);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ResponseAccountDTO responseBody = (ResponseAccountDTO) response.getBody();
        assertNotNull(responseBody);
        assertEquals(newClient.getUsername(), responseBody.getUsername());
        assertEquals(newClient.getEmail(), responseBody.getEmail());

        verify(accountRepository).findByUserName(newClient.getUsername());
        verify(roleRepository).findByType("cliente");
        verify(accountRepository).save(any(Cuenta.class));
        verify(paymentClient).addPayment(any(NewPaymentDTO.class));
    }

}
