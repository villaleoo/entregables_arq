package Monopatin_servicio.Service;

import Monopatin_servicio.Dto.MonopatinDTO;
import Monopatin_servicio.Entity.Monopatin;
import Monopatin_servicio.Entity.Parada;
import Monopatin_servicio.Repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository repo;

    public List<MonopatinDTO> listarTodos() {
        List<Monopatin> lista = repo.findAll();
        List<MonopatinDTO> monopatines = new ArrayList<>();
        for(Monopatin m : lista) {
            monopatines.add(new MonopatinDTO(m.isDisponible(), m.getMinutos_uso(), m.getKm_uso()));
        }
        return monopatines;
    }

    public MonopatinDTO listarUno(Integer id) {
        Monopatin m = repo.findById(id).orElse(null);
        MonopatinDTO monopatin = new MonopatinDTO();
        if(m != null) {
            monopatin.setDisponible(m.isDisponible());
            monopatin.setMinutos_uso(m.getMinutos_uso());
            monopatin.setKm_uso(m.getKm_uso());
        }
        return monopatin;
    }

    //    public void addToStop()
    public void nuevoMonopatin(Monopatin m) {
        repo.save(m);
    }

    public void borrarMonopatin(Integer id) {
        repo.deleteById(id);
    }

    public void proxParada(Integer id, Parada p) {
        repo.findById(id).ifPresent(m -> m.setParada(p));
    }
}
