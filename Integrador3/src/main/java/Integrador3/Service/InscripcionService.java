package Integrador3.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Integrador3.DTO.InscripcionDTO;
import Integrador3.DTO.ReporteDTO;
import Integrador3.Entities.Carrera;
import Integrador3.Entities.Estudiante;
import Integrador3.Entities.Inscripcion;
import Integrador3.Entities.InscripcionID;
import Integrador3.Repository.ICarreraRepository;
import Integrador3.Repository.IEstudianteRepository;
import Integrador3.Repository.IInscripcionRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Autowired
    private ICarreraRepository carreraRepository;

    @Autowired
    private IEstudianteRepository estudianteRepository;

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
