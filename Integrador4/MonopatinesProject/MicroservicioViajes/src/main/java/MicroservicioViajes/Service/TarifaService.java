package MicroservicioViajes.Service;

import MicroservicioViajes.DTO.TarifaDTO;
import MicroservicioViajes.Entities.Tarifa;
import MicroservicioViajes.Repository.TarifaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TarifaService {
    @Autowired
    private TarifaRepository tarifaRepository;

    public void add(TarifaDTO tarifaDTO) {
        Tarifa t = new Tarifa();
        t.setTarifa(tarifaDTO.getTarifa());
        t.setTipo(tarifaDTO.getTipo());
        t.setFecha(tarifaDTO.getFecha());
        tarifaRepository.save(t);
    }

    public TarifaDTO getById(Long id) {
        Tarifa t = tarifaRepository.findById(id).orElse(null);
        try {
            return new TarifaDTO(t.getTarifa(), t.getTipo(), t.getFecha());
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No se encontro tarifa  con id: " + id);

        }
    }

    public Page<TarifaDTO> getAll(Pageable pageable) {
        Page<Tarifa> tarifas = tarifaRepository.findAll(pageable);
        List<TarifaDTO> res = new LinkedList<>();

        for (Tarifa t : tarifas)
            res.add(new TarifaDTO(t.getTarifa(), t.getTipo(), t.getFecha()));

        return new PageImpl<>(res, pageable, tarifas.getTotalElements());
    }

    public void delete(Long id) {
        if (!tarifaRepository.existsById(id))
            throw new EntityNotFoundException("Tarifa no encontrada con id: " + id);
        tarifaRepository.deleteById(id);
    }

    public Tarifa update(Long id, TarifaDTO tarifa) {
        if (!tarifaRepository.existsById(id))
            throw new EntityNotFoundException("Tarifa no encontrada con id: " + id);
        Tarifa t = tarifaRepository.findById(id).orElse(null);
        t.setTarifa(tarifa.getTarifa());
        t.setTipo(tarifa.getTipo());
        t.setFecha(tarifa.getFecha());
        tarifaRepository.save(t);
        return t;
    }
}
