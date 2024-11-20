package org.example.microgestionparadas;

import org.example.microgestionparadas.DTO.StopAvailabilityDTO;
import org.example.microgestionparadas.DTO.StopDTO;
import org.example.microgestionparadas.DTO.VisibleDataStopDTO;
import org.example.microgestionparadas.feignClient.MonoClient;
import org.example.microgestionparadas.model.Parada;
import org.example.microgestionparadas.repository.StopRepository;
import org.example.microgestionparadas.services.StopService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ParadasTests {

    @Mock
    StopRepository repository;
    @Mock
    MonoClient monoClient;
    @InjectMocks
    StopService service;


    @Test
    public void testCreateStop(){
        VisibleDataStopDTO entry = new VisibleDataStopDTO("Stop 1", "Address 1", new Point(5,5));


        VisibleDataStopDTO result = service.save(entry);


        assertEquals(entry, result, "El dto devuelto debe ser el mismo que el dto de entrada");

    }

    @Test
    public void testFindAll() {
        Parada parada1 = new Parada("Parada 1", "Direccion 1", new Point(5,5));
        Parada parada2 = new Parada("Parada 2", "Direccion 2", new Point(10,10));

        when(repository.findAll()).thenReturn(Arrays.asList(parada1, parada2));

        List<Parada> result = service.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());


        verify(repository).findAll();
    }

    @Test
    public void testFindById(){
        Long id = 1L;
        Parada parada = new Parada("Parada 1", "Dirección 1", new Point(5,5));
        parada.setId_stop(id);

        when(repository.findById(id)).thenReturn(Optional.of(parada));

        StopDTO result = service.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId_stop());
        assertEquals("Parada 1", result.getName());
        assertEquals("Dirección 1", result.getAdress());
        assertEquals(new Point((int)parada.getX(), (int)parada.getY()), result.getLocation());

        verify(repository).findById(id);
    }

    @Test
    public void testUpdate(){
        Long id = 1L;
        Parada existingParada = new Parada("Parada 1", "Dirección 1", new Point(5,5));
        existingParada.setId_stop(id);

        VisibleDataStopDTO entry = new VisibleDataStopDTO("Nuevo nombre", "Nueva dirección", new Point(10, 20));

        when(repository.findById(id)).thenReturn(Optional.of(existingParada));

        VisibleDataStopDTO result = service.update(id, entry);

        assertNotNull(result);
        assertEquals(entry.getName(), result.getName());
        assertEquals(entry.getAdress(), result.getAdress());
        assertEquals(entry.getLocation(), result.getLocation());

        verify(repository).save(existingParada);
    }

    @Test
    public void testDelete(){
        Long id = 1L;
        Parada existingParada = new Parada("Parada 1", "Dirección 1", new Point(5,5));
        existingParada.setId_stop(id);

        when(repository.findById(id)).thenReturn(Optional.of(existingParada));

        VisibleDataStopDTO result = service.delete(id);

        assertNotNull(result);
        assertEquals(existingParada.getName(), result.getName());
        assertEquals(existingParada.getAdress(), result.getAdress());
        assertEquals(new Point((int)existingParada.getX(), (int)existingParada.getY()), result.getLocation());

        verify(repository).delete(existingParada);
    }
}
