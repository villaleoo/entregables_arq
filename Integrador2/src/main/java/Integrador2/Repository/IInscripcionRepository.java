package Integrador2.Repository;

import Integrador2.DTO.ReporteDTO;
import Integrador2.Entities.Carrera;
import Integrador2.Entities.Estudiante;

import java.time.LocalDate;
import java.util.List;

public interface IInscripcionRepository {

    void matricularEstudiante(int dniEstudiante, String nombreCarrera, LocalDate fechaInscripcion);

    List<ReporteDTO> getReporte();

}
