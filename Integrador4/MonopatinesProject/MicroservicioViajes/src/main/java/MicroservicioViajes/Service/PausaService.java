package MicroservicioViajes.Service;

import MicroservicioViajes.DTO.PausaDTO;
import MicroservicioViajes.Entities.Pausa;
import MicroservicioViajes.Repository.PausaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PausaService {
    @Autowired
    private PausaRepository pausaRepository;

    public void add(PausaDTO pausaDTO) {
        Pausa p = new Pausa();
        p.setFechaInicio(pausaDTO.getFechaInicio());
        p.setFechaFin(pausaDTO.getFechaFin());
        pausaRepository.save(p);
    }

    public PausaDTO getById(Long id) {
        Pausa p = pausaRepository.findById(id).orElse(null);
        if (p != null)
            return new PausaDTO( p.getFechaInicio(), p.getFechaFin(), p.getLimitePausa());
        return null;
    }

    public Page<PausaDTO> getAll(Pageable pageable) {
        Page<Pausa> pausas = pausaRepository.findAll(pageable);
        List<PausaDTO> res = new LinkedList<>();

        for (Pausa p : pausas)
            res.add(new PausaDTO(p.getFechaInicio(), p.getFechaFin(), p.getLimitePausa()));

        return new PageImpl<>(res, pageable, pausas.getTotalElements());
    }

    public void delete(Long id) {
        if (!pausaRepository.existsById(id))
            throw new EntityNotFoundException("Pausa no encontrada con id: " + id);
        pausaRepository.deleteById(id);
    }

    public Pausa update(Long id, PausaDTO pausa) {
        if (!pausaRepository.existsById(id))
            throw new EntityNotFoundException("Pausa no encontrada con id: " + id);
        Pausa p = pausaRepository.findById(id).orElse(null);
        p.setFechaInicio(pausa.getFechaInicio());
        p.setFechaFin(pausa.getFechaFin());
        pausaRepository.save(p);
        return p;
    }
}
