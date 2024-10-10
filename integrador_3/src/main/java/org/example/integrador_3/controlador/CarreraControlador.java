package org.example.integrador_3.controlador;

import org.example.integrador_3.persistencia.DTO.CarreraDto;
import org.example.integrador_3.persistencia.DTO.EstudianteDto;
import org.example.integrador_3.persistencia.DTO.ReporteDto;
import org.example.integrador_3.persistencia.modelo.Carrera;
import org.example.integrador_3.persistencia.modelo.Estudiante;
import org.example.integrador_3.repositorio.CarreraRepo;
import org.example.integrador_3.servicio.CarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarreraControlador {
    @Autowired
    private final CarreraServicio r;
    public CarreraControlador(CarreraServicio repo) {
        this.r = repo;
    }

    @GetMapping("/carreras")
    public Iterable<Carrera> traerCarreras() { return r.traerCarreras(); }

    @GetMapping("/carreras/{id}")
    Optional<Carrera> traerUnaCarrera(@PathVariable Integer id) { return r.traerUnaCarrera(id);}

    @GetMapping("/carreras/cantidadestudiantes")
    public Iterable<CarreraDto> traerCarrerasPorCantEstudiantes() { return r.traerCarrerasPorCantEstudiantes(); }

    @GetMapping("/carreras/reporte")
    public Iterable<ReporteDto> traerReporte() {
        return r.traerReporte();
    }

    @PostMapping("/carreras/nuevo")
    Carrera nuevaCarrera(@RequestBody Carrera c) { return r.nuevaCarrera(c); }
}
