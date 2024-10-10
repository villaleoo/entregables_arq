package org.example.integrador_3.controlador;

import org.example.integrador_3.persistencia.DTO.EstudianteDto;
import org.example.integrador_3.persistencia.modelo.Estudiante;
import org.example.integrador_3.repositorio.EstudianteRepo;
import org.example.integrador_3.servicio.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EstudianteControlador {
    @Autowired
    private final EstudianteServicio r;
    public EstudianteControlador(EstudianteServicio repo) {
        this.r = repo;
    }

    @GetMapping("/estudiantes")
    public Iterable<Estudiante> traerEstudiantes() { return r.traerEstudiantes(); }

    @GetMapping("/estudiantes/{id}")
    Optional<Estudiante> traerUnEstudiante(@PathVariable Integer id) { return r.traerUnEstudiante(id);}

    @GetMapping("/estudiantes/nrolibreta/{nro_libreta}")
    EstudianteDto traerEstudiantePorNroLibreta(@PathVariable Integer nro_libreta) {
        return r.traerEstudiantePorNroLibreta(nro_libreta);
    }

    @GetMapping("/estudiantes/genero/{genero}")
    List<EstudianteDto> traerEstudiantesPorGenero(@PathVariable String genero) {
        return r.traerEstudiantesPorGenero(genero);
    }

    @GetMapping("/estudiantes/{carrera}/{ciudad}")
    List<EstudianteDto> traerEstudiantesPorCarreraCiudad(@PathVariable String carrera, @PathVariable String ciudad) {
        return r.traerEstudiantesPorCarreraCiudad(carrera, ciudad);
    }

    @PostMapping("estudiantes/nuevo")
    Estudiante nuevoEstudiante(@RequestBody Estudiante e) { return r.nuevoEstudiante(e); }

}
