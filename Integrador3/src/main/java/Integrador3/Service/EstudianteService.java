package Integrador3.Service;

import Integrador3.DTO.CarreraDTO;
import Integrador3.DTO.CarreraInscriptosDTO;
import Integrador3.Entities.Carrera;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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


    public void addEstudiante(EstudianteDTO estudiante) {
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

    public EstudianteDTO getEstudianteById(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if (estudiante != null)
            return new EstudianteDTO(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(),
                    estudiante.getGenero(), estudiante.getCiudad(), estudiante.getNroLibreta());
        return null;
    }

    public List<EstudianteDTO> getAllEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<EstudianteDTO> res = new LinkedList<>();

        for (Estudiante c : estudiantes)
            res.add(new EstudianteDTO(c.getDocumento(), c.getNombre(), c.getApellido(), c.getEdad(),
                    c.getGenero(), c.getCiudad(), c.getNroLibreta()));

        return res;
    }

    public List<EstudianteDTO> getEstudiantesOrderedBy(String criterio) {
        boolean encontrado = Arrays.stream(EstudianteDTO.class.getDeclaredFields())
                .anyMatch(campo -> campo.getName().equals(criterio));
        if (encontrado) {
            List<Estudiante> estudiantes = estudianteRepository.findAll();
            List<EstudianteDTO> res = new LinkedList<>();

            Comparator<Estudiante> comparator;
            switch (criterio) {
                case "nombre":
                    comparator = Comparator.comparing(Estudiante::getNombre);
                    break;
                case "apellido":
                    comparator = Comparator.comparing(Estudiante::getApellido);
                    break;
                case "edad":
                    comparator = Comparator.comparing(Estudiante::getEdad);
                    break;
                case "genero":
                    comparator = Comparator.comparing(Estudiante::getGenero);
                    break;
                case "documento":
                    comparator = Comparator.comparing(Estudiante::getDocumento);
                    break;
                case "ciudad":
                    comparator = Comparator.comparing(Estudiante::getCiudad);
                    break;
                case "nroLibreta":
                    comparator = Comparator.comparing(Estudiante::getNroLibreta);
                    break;
                default:
                    throw new IllegalArgumentException("Criterio no válido: " + criterio);
            }

            estudiantes.sort(comparator);
            for (Estudiante e : estudiantes) {
                EstudianteDTO aux = new EstudianteDTO(e.getDocumento(), e.getNombre(), e.getApellido(), e.getEdad(), e.getGenero(), e.getCiudad(), e.getNroLibreta());
                res.add(aux);
            }
            return res;
        }

        throw new IllegalArgumentException("Criterio no válido: " + criterio);
    }

    public EstudianteDTO getEstudianteByNroLibreta(int nroLibreta) {
        EstudianteDTO est = estudianteRepository.getEstudianteByNroLibreta(nroLibreta);
        if (est != null)
            return est;
        else throw new EntityNotFoundException("Estudiante no encontrado con nro librreta: " + nroLibreta);
    }

    public List<EstudianteDTO> getEstudiantesByGenero(String genero) {
        return estudianteRepository.getEstudiantesByGenero(genero);
    }

    public List<EstudianteCarreraDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad) {
        return estudianteRepository.getEstudiantesByCarreraAndCiudad(carrera, ciudad);
    }

    public void deleteEstudiante(Long id) {
        if (!estudianteRepository.existsById(id))
            throw new EntityNotFoundException("Estudiante no encontrado con id: " + id);
        estudianteRepository.deleteById(id);
    }

    public EstudianteDTO updateEstudiante(Long id, EstudianteDTO estudiante) {
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
