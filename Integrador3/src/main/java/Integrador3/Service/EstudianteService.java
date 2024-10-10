package Integrador3.Service;

import Integrador3.DTO.CarreraDTO;
import Integrador3.DTO.CarreraInscriptosDTO;
import Integrador3.Entities.Carrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Integrador3.DTO.EstudianteCarreraDTO;
import Integrador3.DTO.EstudianteDTO;
import Integrador3.Entities.Estudiante;
import Integrador3.Repository.EstudianteRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    public void addEstudiante(EstudianteDTO estudiante) {
        Estudiante newEstudiante = new Estudiante();
        newEstudiante.setNombre(estudiante.getNombre());
        newEstudiante.setApellido(estudiante.getApellido());
        newEstudiante.setEdad(estudiante.getEdad());
        newEstudiante.setGenero(estudiante.getGenero());
        newEstudiante.setDocumento(estudiante.getDocumento());
        newEstudiante.setCiudad(estudiante.getCiudad());
        newEstudiante.setNroLibreta(estudiante.getNroLibreta());
        estudianteRepository.save(newEstudiante);
    }

    public EstudianteDTO getEstudianteById(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);

        if (estudiante != null)
            return new EstudianteDTO(estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(),
                    estudiante.getGenero(), estudiante.getDocumento(), estudiante.getCiudad(), estudiante.getNroLibreta());
        return null;
    }

    public List<EstudianteDTO> getAllEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<EstudianteDTO> res = new LinkedList<>();

        for (Estudiante c : estudiantes)
            res.add(new EstudianteDTO(c.getNombre(), c.getApellido(), c.getEdad(),
                    c.getGenero(), c.getDocumento(), c.getCiudad(), c.getNroLibreta()));

        return res;
    }

    public List<EstudianteDTO> getEstudiantesOrderedBy(String criterio) {
        boolean encontrado = Arrays.stream(EstudianteDTO.class.getDeclaredFields())
                .anyMatch(campo -> campo.getName().equals(criterio));
        if (encontrado)
            return estudianteRepository.getEstudiantesOrderedBy(criterio);
        return new LinkedList<>();
    }

    public EstudianteDTO getEstudianteByNroLibreta(int nroLibreta) {
        return estudianteRepository.getEstudianteByNroLibreta(nroLibreta);
    }

    public List<EstudianteDTO> getEstudiantesByGenero(String genero) {
        return estudianteRepository.getEstudiantesByGenero(genero);
    }

    public List<EstudianteCarreraDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad) {
        return estudianteRepository.getEstudiantesByCarreraAndCiudad(carrera, ciudad);
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    public EstudianteDTO updateEstudiante(Long id, EstudianteDTO estudiante) {
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
