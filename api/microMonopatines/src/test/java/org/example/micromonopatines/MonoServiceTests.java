package org.example.micromonopatines;

import org.example.micromonopatines.DTO.MonoDTO;
import org.example.micromonopatines.DTO.externals.IdTravelDTO;
import org.example.micromonopatines.DTO.externals.StopDTO;
import org.example.micromonopatines.DTO.externals.TotalTravelsDTO;
import org.example.micromonopatines.DTO.location.KmsLocationDTO;
import org.example.micromonopatines.DTO.location.LocationDTO;
import org.example.micromonopatines.DTO.reports.ReportUsagePauseDTO;
import org.example.micromonopatines.exceptions.MonopatinNotFoundException;
import org.example.micromonopatines.feignClient.StopClient;
import org.example.micromonopatines.feignClient.TravelClient;
import org.example.micromonopatines.model.Monopatin;
import org.example.micromonopatines.repository.MonoRepository;
import org.example.micromonopatines.services.MonoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class MonoServiceTests {

    @Mock
    private MonoRepository repository;

    @Mock
    private StopClient stopClient;

    @Mock
    private TravelClient travelClient;

    @InjectMocks
    private MonoService service;

    @Test
    public void testCrearMonopatin() {

        StopDTO stop = new StopDTO(1L, "parada zona centro", "pinto 1515", new Point(5, 5));
        Monopatin esperado = new Monopatin(true, new Point(5, 5), 0.0, stop);
        MonoDTO provided = new MonoDTO(esperado.isAvailable(), esperado.getStop_assign().getId_stop(), esperado.getKms());


        Mockito.when(stopClient.findById(Mockito.eq(esperado.getStop_assign().getId_stop())))
                .thenReturn(ResponseEntity.ok(stop));

        Mockito.when(repository.save(Mockito.any(Monopatin.class)))
                .thenReturn(esperado);


        MonoDTO resultado = service.save(provided);


        Assertions.assertEquals(provided, resultado);

        Mockito.verify(stopClient).findById(esperado.getStop_assign().getId_stop());
        Mockito.verify(repository).save(Mockito.any(Monopatin.class));
    }

    @Test
    public void testEliminarMonopatin(){
        StopDTO stop = new StopDTO(1L, "parada zona centro", "pinto 1515", new Point(5, 5));
        Monopatin esperado = new Monopatin(true, new Point(5, 5), 0.0, stop);
        esperado.setId_monopatin("idPrueba");

        Mockito.when(repository.findById(esperado.getId_monopatin()))
                .thenReturn(Optional.of(esperado));

        Mockito.doNothing().when(repository).delete(esperado);

        Monopatin resultado = service.delete(esperado.getId_monopatin());

        Assertions.assertEquals(esperado,resultado);

    }

    @Test
    public void testGenerateUsageReport(){
        StopDTO stop = new StopDTO(1L, "parada zona centro", "pinto 1515", new Point(5, 5));
        Monopatin mono = new Monopatin(true, new Point(5, 5), 0.0, stop);
        mono.setId_monopatin("idFake");

        TotalTravelsDTO t = new TotalTravelsDTO(50L,180L,50L);

        ReportUsagePauseDTO esperado = new ReportUsagePauseDTO("idFake",(long) mono.getKms(),t.getTotal_min_traveled(),t.getTotal_travels(),t.getTotal_min_pause());

        Mockito.when(travelClient.getDetailsTravelsMonopatin("idFake"))
                .thenReturn(ResponseEntity.ok(t));

        Mockito.when(repository.findById("idFake"))
                .thenReturn(Optional.of(mono));


        Object result = this.service.generateUsageReport("idFake",true);

        Assertions.assertEquals(esperado,result);


    }

    @Test
    public void testFailedUsageReport(){
        StopDTO stop = new StopDTO(1L, "parada zona centro", "pinto 1515", new Point(5, 5));
        Monopatin mono = new Monopatin(true, new Point(5, 5), 0.0, stop);
        mono.setId_monopatin("idFake");

        TotalTravelsDTO t = new TotalTravelsDTO(50L,180L,50L);

        Mockito.when(travelClient.getDetailsTravelsMonopatin("idFake"))
                .thenReturn(ResponseEntity.ok(t));

        Mockito.when(repository.findById("idFake"))
                .thenReturn(Optional.empty());


        MonopatinNotFoundException exception = Assertions.assertThrows(
                MonopatinNotFoundException.class,
                () -> this.service.generateUsageReport("idFake", true)
        );

        Assertions.assertEquals("No se econtro monopatin con id: idFake", exception.getMessage());
    }

    @Test
    public void testUpdateLocationInTravel(){
        StopDTO stop = new StopDTO(1L, "parada zona centro", "pinto 1515", new Point(5, 5));
        Monopatin mono = new Monopatin(true, new Point(5, 5), 0.0, stop);
        mono.setId_monopatin("idFake");
        IdTravelDTO fakeIdTravel= new IdTravelDTO("idTravelFake");

        /*si el metodo funciona bien, al pasar de 5,5 a 6,6 solo incrementa 1km el monopatin -> pasando de 0.0 a 1.0kms*/
        LocationDTO location = new LocationDTO(new Point(6,6));
        KmsLocationDTO esperado = new KmsLocationDTO(location.getLocation(),1.0);

        Mockito.when(repository.findById("idFake"))
                .thenReturn(Optional.of(mono));

        Mockito.when(travelClient.getIdTravelInProgressOfMonopatin("idFake"))
                .thenReturn(new ResponseEntity<>(fakeIdTravel,HttpStatus.OK));

        KmsLocationDTO result = service.updateLocationInTravel("idFake",location);

        Assertions.assertEquals(esperado,result);

    }


}
