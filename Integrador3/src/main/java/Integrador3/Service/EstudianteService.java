package Integrador3.Service;

import Integrador3.DTO.*;
import Integrador3.Entities.Carrera;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import Integrador3.Entities.Estudiante;
import Integrador3.Repository.EstudianteRepository;

import java.util.*;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    public void add(EstudianteDTO estudiante) {
        if (estudianteRepository.existsById((long) estudiante.getDocumento()))
            throw new DuplicateKeyException("Ya existe un estudiante con el documento que estás intentando ingresar" + estudiante.getDocumento());

        Estudiante newEstudiante = new Estudiante();
        newEstudiante.setDocumento(estudiante.getDocumento());
        newEstudiante.setNombre(estudiante.getNombre());
        newEstudiante.setApellido(estudiante.getApellido());
        newEstudiante.setEdad(estudiante.getEdad());
        newEstudiante.setGenero(estudiante.getGenero());
        newEstudiante.setCiudad(estudiante.getCiudad());
        newEstudiante.setNroLibreta(estudiante.getNroLibreta());
        estudianteRepository.save(newEstudiante);
    }

    public EstudianteDTO getById(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if (estudiante != null)
            return new EstudianteDTO(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(),
                    estudiante.getGenero(), estudiante.getCiudad(), estudiante.getNroLibreta());
        return null;
    }

    public Page<EstudianteDTO> getAll(Pageable pageable) {
        Page<Estudiante> estudiantes = estudianteRepository.findAll(pageable);
        List<EstudianteDTO> res = new LinkedList<>();

        for (Estudiante c : estudiantes)
            res.add(new EstudianteDTO(c.getDocumento(), c.getNombre(), c.getApellido(), c.getEdad(),
                    c.getGenero(), c.getCiudad(), c.getNroLibreta()));

        return new PageImpl<>(res, pageable, estudiantes.getTotalElements());
    }

    public Page<EstudianteDTO> getEstudiantesOrderedBy(Pageable pageable) {
        return estudianteRepository.getEstudiantesOrderedBy(pageable);
    }

    public List<EstudianteDTO> getEstudiantesByAttribute(EstudianteSearchDTO request) {
        return estudianteRepository.getEstudiantesByAttribute(request.getDocumento(), request.getNombre(), request.getApellido()
                , request.getEdad(), request.getGenero(), request.getCiudad(), request.getNroLibreta(), request.getIdCarrera(), request.getNombreCarrera());
    }

    public void delete(Long id) {
        if (!estudianteRepository.existsById(id))
            throw new EntityNotFoundException("Estudiante no encontrado con id: " + id);
        estudianteRepository.deleteById(id);
    }

    public EstudianteDTO update(Long id, EstudianteDTO estudiante) {
        if (!estudianteRepository.existsById(id))
            throw new EntityNotFoundException("Estudiante no encontrado con id: " + id);
        Estudiante newEstudiante = estudianteRepository.findById(id).orElse(null);
        newEstudiante.setNombre(estudiante.getNombre());
        newEstudiante.setApellido(estudiante.getApellido());
        newEstudiante.setEdad(estudiante.getEdad());
        newEstudiante.setGenero(estudiante.getGenero());
        newEstudiante.setDocumento(estudiante.getDocumento());
        newEstudiante.setCiudad(estudiante.getCiudad());
        newEstudiante.setNroLibreta(estudiante.getNroLibreta());
        estudianteRepository.save(newEstudiante);
        return estudiante;
    }


}
