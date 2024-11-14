package MicroservicioMonopatin.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ReportePorDisponibilidadDTO {
    private List<MonopatinDisponibleDTO> disponibles;
    private List<MonopatinDisponibleDTO> enMantenimiento;

    public ReportePorDisponibilidadDTO(List<MonopatinDisponibleDTO> disponibles, List<MonopatinDisponibleDTO> enMantenimiento) {
        this.disponibles = disponibles;
        this.enMantenimiento = enMantenimiento;
    }
}
