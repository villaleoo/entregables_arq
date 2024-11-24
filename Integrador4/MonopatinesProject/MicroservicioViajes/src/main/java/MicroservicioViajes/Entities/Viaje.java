package MicroservicioViajes.Entities;

import MicroservicioViajes.DTO.ViajeDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date fechaInicio;

    @Column
    private Date fechaFin;

    @Column
    private double kmsRecorridos;

    @Column
    private double tiempo;

    @Column
    private double precio;

    @Column
    private Long idMonopatin;

    @Column
    private Long idCuenta;

    @Column
    private Long idUsuario;

    @ManyToOne
    private Tarifa tarifa;

    @ManyToOne
    private Tarifa tarifaExtra;

    @Column
    private Long idParadaInicio;

    @Column
    private Long idParadaFin;

    @Column
    private Boolean enPausa;

    @OneToMany
    private List<Pausa> pausas;

    public Viaje(Long idUsuario, Long idCuenta, Long idMonopatin, Long idParadaInicio, Long idParadaFin, Tarifa tarifa) {
        this.fechaInicio = new Date();
        this.fechaFin = null;
        this.kmsRecorridos = 0;
        this.tiempo = 0;
        this.precio = 0;
        this.idParadaInicio = idParadaInicio;
        this.idParadaFin = idParadaFin;
        this.idCuenta = idCuenta;
        this.idMonopatin = idMonopatin;
        this.idUsuario = idUsuario;
        this.tarifa = tarifa;
        this.tarifaExtra = null;
        this.enPausa = false;
        this.pausas = new LinkedList<>();
    }

    public Viaje(ViajeDTO viajeDTO) {
        this.fechaInicio = viajeDTO.getFechaInicio();
        this.fechaFin = viajeDTO.getFechaFin();
        this.kmsRecorridos = viajeDTO.getKmsRecorridos();
        this.tiempo = viajeDTO.getTiempo();
        this.idMonopatin = viajeDTO.getIdMonopatin();
        this.idUsuario = viajeDTO.getIdUsuario();
        this.idCuenta = viajeDTO.getIdCuenta();
        this.idParadaInicio = viajeDTO.getIdParadaInicio();
        this.idParadaFin = viajeDTO.getIdParadaFin();
        this.precio = 0;
        this.tarifaExtra = null;
        this.enPausa = false;
        this.pausas = new LinkedList<>();

    }

    public void addPausa(Pausa p) {
        if (!this.pausas.contains(p))
            this.pausas.add(p);
        else
            this.pausas.add(pausas.indexOf(p), p);
    }
}
