package MicroservicioMonopatin.Service;


import MicroservicioMonopatin.DTO.MonopatinDTO;
import MicroservicioMonopatin.DTO.ParadaDTO;
import MicroservicioMonopatin.Entities.Monopatin;
import MicroservicioMonopatin.Entities.Parada;
import MicroservicioMonopatin.Repository.MonopatinRepository;
import MicroservicioMonopatin.Repository.ParadaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

@Service
public class ParadaService {
    @Autowired
    private ParadaRepository paradaRepository;

    @Autowired
    private MonopatinRepository monopatinRepository;

    public void add(ParadaDTO paradaDTO) {
        Parada p = new Parada();
        p.setX(paradaDTO.getX());
        p.setY(paradaDTO.getY());
        p.setNombre(paradaDTO.getNombre());
        p.setDireccion(paradaDTO.getDireccion());
        paradaRepository.save(p);
    }

    public ParadaDTO getById(Long id) {
        Parada p = paradaRepository.findById(id).orElse(null);
        List<MonopatinDTO> monopatines = new LinkedList<>();
        for (Monopatin m : p.getMonopatines())
            monopatines.add(new MonopatinDTO(m));
        if (p != null)
            return new ParadaDTO(p.getX(), p.getY(), p.getNombre(), p.getDireccion(), monopatines, p.getMonopatines().size());
        throw new EntityNotFoundException("No se encontro parada  con id: " + id);

    }

    public Page<ParadaDTO> getAll(Pageable pageable) {
        Page<Parada> paradas = paradaRepository.findAll(pageable);
        List<ParadaDTO> res = new LinkedList<>();

        for (Parada p : paradas) {
            List<MonopatinDTO> monopatines = new LinkedList<>();
            for (Monopatin m : p.getMonopatines())
                monopatines.add(new MonopatinDTO(m));
            res.add(new ParadaDTO(p.getX(), p.getY(), p.getNombre(), p.getDireccion(), monopatines, p.getMonopatines().size()));
        }

        return new PageImpl<>(res, pageable, paradas.getTotalElements());
    }

    public void delete(Long id) {
        if (!paradaRepository.existsById(id))
            throw new EntityNotFoundException("Parada no encontrada con id: " + id);
        paradaRepository.deleteById(id);
    }

    public Parada update(Long id, ParadaDTO parada) {
        if (!paradaRepository.existsById(id))
            throw new EntityNotFoundException("Parada no encontrada con id: " + id);
        Parada p = paradaRepository.findById(id).orElse(null);
        p.setX(parada.getX());
        p.setY(parada.getY());
        p.setNombre(parada.getNombre());
        p.setDireccion(parada.getDireccion());
        List<Monopatin> monopatines = new LinkedList<>();
        for (MonopatinDTO m : parada.getMonopatines())
            monopatines.add(new Monopatin(m));
        p.setMonopatines(monopatines);
        paradaRepository.saveAndFlush(p);
        return p;
    }

    public void agregarMonopatinaParada(Long id, MonopatinDTO monopatinDTO) {
        Parada parada = paradaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parada no encontrada"));

        Monopatin monopatin = monopatinRepository.findById(monopatinDTO.getId()).orElse(null);
        if (monopatin != null)
            parada.addMonopatin(monopatin);
        paradaRepository.save(parada);
    }


    public Page<ParadaDTO> getParadasCercanas(Pageable pageable, int x, int y) {
        return paradaRepository.getParadasCercanas(pageable, x, y);
    }
}
