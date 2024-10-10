package Integrador3.Service;

import Integrador3.DTO.CarreraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Integrador3.DTO.InscripcionDTO;
import Integrador3.DTO.ReporteDTO;
import Integrador3.Entities.Carrera;
import Integrador3.Entities.Estudiante;
import Integrador3.Entities.Inscripcion;
import Integrador3.Entities.InscripcionID;
import Integrador3.Repository.CarreraRepository;
import Integrador3.Repository.EstudianteRepository;
import Integrador3.Repository.InscripcionRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public void addInscripcion(InscripcionDTO inscripcion) {
        Carrera carrera = carreraRepository.findById(inscripcion.getIdCarrera()).orElse(null);
        Estudiante estudiante = estudianteRepository.findById(inscripcion.getIdEstudiante()).orElse(null);

        Inscripcion newInscripcion = new Inscripcion(carrera, estudiante);
        newInscripcion.setId(new InscripcionID(inscripcion.getIdCarrera(), inscripcion.getIdEstudiante()));
        inscripcionRepository.save(newInscripcion);
    }

    public InscripcionDTO getInscripcionById(Long idCarrera, Long idEstudiante) {
        return inscripcionRepository.findById(idCarrera, idEstudiante);
    }

    public void deleteInscripcion(Long idCarrera, Long idEstudiante) {
        inscripcionRepository.deleteById(idCarrera, idEstudiante);
    }

    public InscripcionDTO updateInscripcion(Long idCarrera, Long idEstudiante, InscripcionDTO inscripcion) {
        InscripcionDTO newInscripcion = inscripcionRepository.findById(idCarrera, idEstudiante);

        if (newInscripcion != null) {
            inscripcionRepository.update(inscripcion.getIdCarrera(), inscripcion.getIdEstudiante(), inscripcion.getFechaInscripcion(), inscripcion.getFechaGraduacion());
            return inscripcion;
        }
        return null;
    }

    public List<InscripcionDTO> getAllInscripciones() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        List<InscripcionDTO> res = new LinkedList<>();

        for (Inscripcion i : inscripciones)
            res.add(new InscripcionDTO(i.getCarrera().getIdCarrera(), i.getEstudiante().getIdEstudiante(), i.getFechaInscripcion(), i.getFechaGraduacion()));

        return res;
    }

    public List<ReporteDTO> getReporte() {
        List<ReporteDTO> lista = inscripcionRepository.getReporte();
        return lista;
    }
}
