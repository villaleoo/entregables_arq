package org.example.microusers;

import org.example.microusers.DTO.AccountDTO;
import org.example.microusers.DTO.user.NewUserDTO;
import org.example.microusers.DTO.user.UserDTO;
import org.example.microusers.feignClient.AuthClient;
import org.example.microusers.model.Usuario;
import org.example.microusers.repository.UserRepository;
import org.example.microusers.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    AuthClient authClient;
    @Mock
    UserRepository repository;
    @InjectMocks
    UserService service;

    @Test
    public void testFindAll(){
        Usuario usuario1 = new Usuario();
        usuario1.setName("pepe");
        usuario1.setEmail("pepe@gmail.com");
        usuario1.setPhone("2494887766");
        usuario1.setSurname("hernandez");
        usuario1.setId_account(3L);

        Usuario usuario2 = new Usuario();
        usuario2.setName("pepa");
        usuario2.setEmail("pepa@gmail.com");
        usuario2.setPhone("2494887766");
        usuario2.setSurname("hernandez");
        usuario2.setId_account(3L);

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> result = service.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(usuario1));
        assertTrue(result.contains(usuario2));

        verify(repository).findAll();
    }

    @Test
    public void testFindById(){
        Usuario usuario1 = new Usuario();
        usuario1.setName("pepe");
        usuario1.setEmail("pepe@gmail.com");
        usuario1.setPhone("2494887766");
        usuario1.setSurname("hernandez");
        usuario1.setId_account(1L);
        usuario1.setId_user(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(usuario1));

        Usuario result = service.findById(1L);

        assertNotNull(result);
        assertEquals(usuario1.getId_user(), result.getId_user());
        assertEquals(usuario1.getName(), result.getName());

        verify(repository).findById(1L);

    }
}
