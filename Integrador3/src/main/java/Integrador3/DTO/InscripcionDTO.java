package Integrador3.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Service
public class InscripcionDTO {
    private Long idCarrera;
    private Long documentoEstudiante;
    private LocalDate fechaInscripcion;
    private LocalDate fechaGraduacion;

    public InscripcionDTO(Long idCarrera, Long idEstudiante) {
        this.idCarrera = idCarrera;
        this.documentoEstudiante = documentoEstudiante;

    }

    public InscripcionDTO(Long idCarrera, Long documentoEstudiante, LocalDate fechaInscripcion, LocalDate fechaGraduacion) {
        this.idCarrera = idCarrera;
        this.documentoEstudiante = documentoEstudiante;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = fechaGraduacion;
    }

    public InscripcionDTO(LocalDate fechaInscripcion, LocalDate fechaGraduacion) {
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = fechaGraduacion;
    }
}
