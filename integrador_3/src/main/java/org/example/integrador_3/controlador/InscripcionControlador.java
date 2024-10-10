package org.example.integrador_3.controlador;

import org.example.integrador_3.persistencia.DTO.InscripcionDto;
import org.example.integrador_3.persistencia.modelo.Inscripcion;
import org.example.integrador_3.repositorio.InscripcionRepo;
import org.example.integrador_3.servicio.InscripcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class InscripcionControlador {
    @Autowired
    private final InscripcionServicio r;
    public InscripcionControlador(InscripcionServicio repo) {
        this.r = repo;
    }

    @GetMapping("/inscripciones")
    public Iterable<Inscripcion> traerInscripciones() { return r.traerInscripciones(); }

    @GetMapping("/inscripciones/est/{estudiante_id}/carr/{carrera_id}")
    public InscripcionDto traerUnaInscripcion(@PathVariable Integer estudiante_id, @PathVariable Integer carrera_id) {
        return r.traerUnaInscripcion(estudiante_id, carrera_id);
    }

    @PostMapping("/inscripciones/nuevo")
    Inscripcion matricularEstudiante(@RequestBody Inscripcion i) { return r.matricularEstudiante(i); }
    
}
