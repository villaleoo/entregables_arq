package MicroservicioMonopatin.Service;

import MicroservicioMonopatin.DTO.*;
import MicroservicioMonopatin.Entities.Monopatin;
import MicroservicioMonopatin.FeignClients.ViajeFeignClient;
import MicroservicioMonopatin.Repository.MonopatinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @Autowired
    private ViajeFeignClient viajeFeignClient;

    public void add(MonopatinDTO monopatinDTO) {
        Monopatin m = new Monopatin(monopatinDTO);
        monopatinRepository.save(m);
    }

    public MonopatinDTO getById(Long id) {
        Monopatin m = monopatinRepository.findById(id).orElse(null);
        if (m != null)
            return new MonopatinDTO(m);
        throw new EntityNotFoundException("No se encontro monopatin  con id: " + id);
    }

    public Page<MonopatinDTO> getAll(Pageable pageable) {
        Page<Monopatin> monopatines = monopatinRepository.findAll(pageable);
        List<MonopatinDTO> res = new LinkedList<>();

        for (Monopatin m : monopatines)
            res.add(new MonopatinDTO(m));

        return new PageImpl<>(res, pageable, monopatines.getTotalElements());
    }

    public void delete(Long id) {
        if (!monopatinRepository.existsById(id))
            throw new EntityNotFoundException("Monopatin no encontrado con id: " + id);
        monopatinRepository.deleteById(id);
    }

    public Monopatin update(Long id, MonopatinDTO monopatinDTO) {
        if (!monopatinRepository.existsById(id))
            throw new EntityNotFoundException("Monopatin no encontrada con id: " + id);
        Monopatin m = monopatinRepository.findById(id).orElse(null);
        m.setPatenteMonopatin(monopatinDTO.getPatenteMonopatin());
        m.setDisponible(monopatinDTO.getDisponible());
        m.setEnMantenimiento(monopatinDTO.getEnMantenimiento());
        monopatinRepository.save(m);
        return m;
    }

    public List<MonopatinDTO> getMonopatinesByAttribute(MonopatinDTO request) {
        return monopatinRepository.getMonopatinesByAttribute(request.getPatenteMonopatin(), request.getDisponible(),
                request.getEnMantenimiento());
    }


    public Page<ReporteMonopatinKMsDTO> getReporteMonopatinesPorKMs(Pageable pageable) {
        return viajeFeignClient.getReporteMonopatinesPorKMs(pageable);
    }

    public Page<ReporteMonopatinPausasDTO> getReporteMonopatinesMantenimiento(Pageable pageable, int pausa) {
        return viajeFeignClient.getReporteMonopatinesMantenimiento(pageable, pausa);
    }

    public Page<ReporteMonopatinesPorCantViajesDTO> getReporteMonopatinesPorCantViajes(Pageable pageable, int anio, int cantViajes) {
        return viajeFeignClient.getReporteMonopatinesPorCantViajes(pageable, anio, cantViajes);
    }

    public List<MonopatinDisponibleDTO> getMonopatinByDisponibilidad() {
        return monopatinRepository.getMonopatinByDisponibilidad();
    }

    public List<MonopatinDisponibleDTO> getMonopatinByEnMantenimiento() {
        return monopatinRepository.getMonopatinByEnMantenimiento();
    }

    public Monopatin setEnMantenimiento(Long id) {
        Monopatin m = monopatinRepository.findById(id).orElse(null);
        if (m != null)
            monopatinRepository.setEnMantenimiento(id);
        throw new EntityNotFoundException("No se encontro monopatin  con id: " + id);
    }

    public void setDisponibilidad(Long id) {
        Monopatin m = monopatinRepository.findById(id).orElse(null);
        if (m == null)
            throw new EntityNotFoundException("No se encontro monopatin  con id: " + id);
        monopatinRepository.setDisponibilidad(id);
    }
}
