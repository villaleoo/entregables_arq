package MicroservicioViajes.DTO;

import MicroservicioViajes.Entities.Pausa;
import MicroservicioViajes.Entities.Tarifa;
import MicroservicioViajes.Entities.Viaje;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViajeDTO {
    private Date fechaInicio;
    private Date fechaFin;
    private double kmsRecorridos;
    private double tiempo;
    private double precio;
    private Long idMonopatin;
    private Long idUsuario;
    private Long idCuenta;
    private double tarifaNormal;
    private double tarifaExtra;
    private Long idParadaInicio;
    private Long idParadaFin;

    public ViajeDTO(Long idMonopatin, Long idUsuario, Long idParadaInicio, Long idCuenta) {
        this.fechaInicio = new Date();
        this.fechaFin = null;
        this.kmsRecorridos = 0;
        this.tiempo = 0;

        this.precio = 0;
        this.idParadaInicio = idParadaInicio;
        this.idParadaFin = null;
        this.idCuenta = idCuenta;
        this.idMonopatin = idMonopatin;
        this.idUsuario = idUsuario;

    }

    public ViajeDTO(Viaje viaje) {
        this.fechaInicio = viaje.getFechaInicio();
        this.fechaFin = viaje.getFechaFin();
        this.tiempo = viaje.getTiempo();
        this.precio = viaje.getPrecio();
        this.idMonopatin = viaje.getIdMonopatin();
        this.idUsuario = viaje.getIdUsuario();
        this.tarifaNormal = viaje.getTarifa().getTarifa();
        if (viaje.getTarifaExtra() != null)
            this.tarifaExtra = viaje.getTarifaExtra().getTarifa();
        this.idParadaInicio = viaje.getIdParadaInicio();
        this.idParadaFin = viaje.getIdParadaFin();
        this.idCuenta = viaje.getIdCuenta();
        this.kmsRecorridos = viaje.getKmsRecorridos();
    }


}
