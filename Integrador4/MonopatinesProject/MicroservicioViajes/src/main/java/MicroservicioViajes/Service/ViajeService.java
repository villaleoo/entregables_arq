package MicroservicioViajes.Service;

import MicroservicioViajes.DTO.*;
import MicroservicioViajes.Entities.Pausa;
import MicroservicioViajes.Entities.Tarifa;
import MicroservicioViajes.Entities.Viaje;
import MicroservicioViajes.FeignClients.MonopatinFeignClient;
import MicroservicioViajes.FeignClients.UsuariosFeignClient;
import MicroservicioViajes.Repository.PausaRepository;
import MicroservicioViajes.Repository.TarifaRepository;
import MicroservicioViajes.Repository.ViajeRepository;
import jakarta.persistence.EntityNotFoundException;
import jdk.jfr.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private TarifaRepository tarifaRepository;
    @Autowired
    private PausaRepository pausaRepository;
    @Autowired
    private MonopatinFeignClient monopatinFeignClient;
    @Autowired
    private UsuariosFeignClient usuariosFeignClient;


    public void add(ViajeDTO viaje) {
        Viaje v = new Viaje(viaje);
        viajeRepository.save(v);
    }

    public ViajeDTO getById(Long id) {
        Viaje v = viajeRepository.findById(id).orElse(null);
        if (v != null) return new ViajeDTO(v);
        throw new EntityNotFoundException("No se encontro viaje  con id: " + id);

    }

    public Page<ViajeDTO> getAll(Pageable pageable) {
        Page<Viaje> viajes = viajeRepository.findAll(pageable);
        List<ViajeDTO> res = new LinkedList<>();

        for (Viaje v : viajes)
            res.add(new ViajeDTO(v));

        return new PageImpl<>(res, pageable, viajes.getTotalElements());
    }

    public void delete(Long id) {
        if (!viajeRepository.existsById(id)) throw new EntityNotFoundException("Viaje no encontrado con id: " + id);
        viajeRepository.deleteById(id);
    }

    public Viaje update(Long id, ViajeDTO viaje) {
        if (!viajeRepository.existsById(id)) throw new EntityNotFoundException("Viaje no encontrado con id: " + id);
        Viaje v = viajeRepository.findById(id).orElse(null);
        v.setFechaInicio(viaje.getFechaInicio());
        v.setFechaFin(viaje.getFechaFin());
        v.setKmsRecorridos(viaje.getKmsRecorridos());
        v.setTiempo(viaje.getTiempo());
        // v.setPausa(viaje.getTiempoPausa());
        v.setPrecio(viaje.getPrecio());
        v.setIdMonopatin(viaje.getIdMonopatin());
        v.setIdUsuario(viaje.getIdUsuario());
        v.setTarifa(v.getTarifa());
        viajeRepository.save(v);
        return v;
    }

    public Page<ReporteMonopatinKMsDTO> getReporteMonopatinesPorKMs(Pageable pageable) {
        return viajeRepository.getReporteMonopatinesPorKMs(pageable);
    }


    public Page<ReporteMonopatinPausasDTO> getReporteMantenimiento(Pageable pageable, int pausa) {
        return viajeRepository.getReporteMantenimiento(pageable, pausa);
    }

    public Page<ReporteMonopatinesPorCantViajesDTO> getReporteMonopatinesPorCantViajes(Pageable pageable, int anio, int cantViajes) {
        return viajeRepository.getReporteMonopatinesPorCantViajes(pageable, anio, cantViajes);
    }

    public Page<ReporteFacturadoPorRangoDTO> getReporteFacturadoEnAnioYMeses(Pageable pageable, int anio, int mesInicio, int mesFin) {
        return viajeRepository.getReporteFacturadoEnAnioYMeses(pageable, anio, mesInicio, mesFin);
    }

    public void iniciarViaje(IniciarViajeDTO initViajeDTO) {
        UsuarioDTO usuario = usuariosFeignClient.getUsuarioById(initViajeDTO.getIdUsuario());
        CuentaDTO cuenta = usuariosFeignClient.getCuentaById(initViajeDTO.getIdCuenta());
        MonopatinDTO monopatin = monopatinFeignClient.getMonopatinById(initViajeDTO.getIdMonopatin());
        ParadaDTO paradaInicio = monopatinFeignClient.getParadaById(initViajeDTO.getIdParadaInicio());
        ParadaDTO paradaFin = monopatinFeignClient.getParadaById(initViajeDTO.getIdParadaFin());
        Tarifa tarifa = tarifaRepository.getTarifaNormalMasCercanaAHoy(Date.from(Instant.now())).get(0);

        if (!monopatin.getDisponible())
            throw new RuntimeException("El monopatin elegido no esta disponible.");

        if (monopatin.getEnMantenimiento())
            throw new RuntimeException("El monopatin elegido esta en mantenimiento.");

        if (paradaInicio.equals(paradaFin))
            throw new RuntimeException("Elegiste parada origen y destino iguales.");

        if (cuenta.getCredito() <= 0)
            throw new RuntimeException("No tenes dinero en la cuenta seleccionada. Saldo actual: " + cuenta.getCredito());

        if (!cuenta.getHabilitada())
            throw new RuntimeException("La cuenta seleccionada no esta habilitada. " + cuenta);

        if (cuenta.getHabilitada() && cuenta.getCredito() > 0 && monopatin.getDisponible() && !monopatin.getEnMantenimiento()) {

            Viaje viaje = new Viaje(initViajeDTO.getIdUsuario(), initViajeDTO.getIdCuenta(), initViajeDTO.getIdMonopatin(),
                    initViajeDTO.getIdParadaInicio(), initViajeDTO.getIdParadaFin(), tarifa);
            viajeRepository.save(viaje);
            monopatinFeignClient.setDisponibilidad(initViajeDTO.getIdMonopatin());
        }
    }

    public ViajeDTO terminarViaje(Long id, TerminarViajeDTO terminarViajeDTO) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);
        if (viaje.getEnPausa()) throw new RuntimeException("No podes finalizar un viaje si esta en pausa. " + viaje);
        if (viaje.getFechaFin() != null) throw new RuntimeException("El viaje ya termino. " + viaje);

        viaje.setKmsRecorridos(terminarViajeDTO.getKmsRecorridos());
        viaje.setFechaFin(Date.from(Instant.now()));


        double tiempoViaje = getMinutos(viaje.getFechaInicio(), viaje.getFechaFin());
        viaje.setTiempo(tiempoViaje);

        double mins = 0;
        boolean limiteExcedido = false;
        //Si hay pausas...
        if (!viaje.getPausas().isEmpty()) {
            for (Pausa p : viaje.getPausas()) {
                double minsPausa = this.getMinutos(p.getFechaInicio(), p.getFechaFin());
                if (minsPausa > p.getLimitePausa()) {
                    limiteExcedido = true;
                    Tarifa tarifaExtra = tarifaRepository.getTarifaExtraMasCercanaAHoy(Date.from(Instant.now())).get(0);
                    viaje.setTarifaExtra(tarifaExtra);
                }
                mins += minsPausa;

            }
            if (limiteExcedido)
                viaje.setPrecio(viaje.getTarifaExtra().getTarifa() * viaje.getTiempo());
        }

        if (!limiteExcedido)
            viaje.setPrecio(viaje.getTarifa().getTarifa() * viaje.getTiempo());

        monopatinFeignClient.setDisponibilidad(viaje.getIdMonopatin());
        viajeRepository.save(viaje);
        return new ViajeDTO(viaje);
    }

    public void pausarViaje(Long idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElse(null);
        if (viaje == null) throw new RuntimeException("El viaje con el id: " + idViaje + " no existe.");
        if (viaje.getEnPausa()) throw new RuntimeException("El viaje ya se encuntra pausado. " + viaje);
        if (viaje.getFechaFin() != null) throw new RuntimeException("El viaje ya termino. " + viaje);

        if (viaje != null) {
            Pausa p = new Pausa(Date.from(Instant.now()), null);
            pausaRepository.save(p);
            viaje.setEnPausa(true);
            viaje.addPausa(p);
            viajeRepository.save(viaje);
        }
    }

    public void reanudarViaje(Long idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElse(null);
        if (!viaje.getEnPausa()) throw new RuntimeException("El viaje no se encuntra pausado. " + viaje);
        if (viaje.getFechaFin() != null) throw new RuntimeException("El viaje ya termino. " + viaje);
        if (viaje != null) {
            Pausa actual = viaje.getPausas().get(viaje.getPausas().size() - 1);
            actual.setFechaFin(Date.from(Instant.now()));
            actual.setTiempoTotalPausa(this.getMinutos(actual.getFechaInicio(), actual.getFechaFin()));
            viaje.setEnPausa(false);
            viajeRepository.save(viaje);
        }
    }

    public double getMinutos(Date fechaInicio, Date fechaFin) {
        Instant instant1 = fechaInicio.toInstant();
        Instant instant2 = fechaFin.toInstant();
        Duration duration = Duration.between(instant1, instant2);
        long minutosDeDiferencia = duration.toMinutes();
        return (double) minutosDeDiferencia;
    }
}
