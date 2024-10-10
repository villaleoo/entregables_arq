package Integrador3.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Service
public class InscripcionDTO {
    private Long idCarrera;
    private Long idEstudiante;
    private LocalDate fechaInscripcion;
    private LocalDate fechaGraduacion;

    public InscripcionDTO(Long idCarrera, Long idEstudiante) {
        this.idCarrera = idCarrera;
        this.idEstudiante = idEstudiante;
    }

    public InscripcionDTO(LocalDate fechaInscripcion, LocalDate fechaGraduacion) {
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = fechaGraduacion;
    }
}
