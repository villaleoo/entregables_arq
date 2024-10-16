package Integrador3.Service;

import Integrador3.DTO.CarreraDTO;
import Integrador3.DTO.CarreraInscriptosDTO;
import Integrador3.Entities.Carrera;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import Integrador3.DTO.EstudianteCarreraDTO;
import Integrador3.DTO.EstudianteDTO;
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

    public List<EstudianteDTO> getAll() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<EstudianteDTO> res = new LinkedList<>();

        for (Estudiante c : estudiantes)
            res.add(new EstudianteDTO(c.getDocumento(), c.getNombre(), c.getApellido(), c.getEdad(),
                    c.getGenero(), c.getCiudad(), c.getNroLibreta()));

        return res;
    }

    public List<EstudianteDTO> getEstudiantesOrderedBy(String criterio) {
        return estudianteRepository.getEstudiantesOrderedBy(Sort.by(criterio));
    }

    public EstudianteDTO getEstudianteByNroLibreta(int nroLibreta) {
        EstudianteDTO est = estudianteRepository.getEstudianteByNroLibreta(nroLibreta);
        if (est != null)
            return est;
        else throw new EntityNotFoundException("Estudiante no encontrado con nro libreta: " + nroLibreta);
    }

    public List<EstudianteDTO> getEstudiantesByGenero(String genero) {
        return estudianteRepository.getEstudiantesByGenero(genero);
    }

    public List<EstudianteCarreraDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad) {
        return estudianteRepository.getEstudiantesByCarreraAndCiudad(carrera, ciudad);
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
