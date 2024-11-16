package Monopatin_servicio.Service;

import Monopatin_servicio.Dto.MonopatinDTO;
import Monopatin_servicio.Dto.ParadaDTO;
import Monopatin_servicio.Entity.Monopatin;
import Monopatin_servicio.Entity.Parada;
import Monopatin_servicio.FeignClient.ParadasFC;
import Monopatin_servicio.Repository.MonopatinRepository;
import Monopatin_servicio.Repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository repo;
    @Autowired
    private TarifaRepository tarifaRepo;

    public List<MonopatinDTO> listarTodos() {
        List<Monopatin> lista = repo.findAll();
        List<MonopatinDTO> monopatines = new ArrayList<>();
        for(Monopatin m : lista) {
            monopatines.add(new MonopatinDTO(m.getId(), m.isDisponible(), m.getMinutos_uso(), m.getMinutos_pausa(),
            m.getKm_uso(), m.getParada().getCoordenada_x(), m.getParada().getCoordenada_y()));
        }
        return monopatines;
    }

    public MonopatinDTO listarUno(Integer id) {
        Monopatin m = repo.findById(id).orElse(null);
        MonopatinDTO monopatin = null;
        if(m != null) {
            monopatin = new MonopatinDTO(m.getId(), m.isDisponible(), m.getMinutos_uso(), m.getMinutos_pausa(),
                    m.getKm_uso(), m.getParada().getCoordenada_x(), m.getParada().getCoordenada_y());
        }
        return monopatin;
    }

    public void nuevoMonopatin(Monopatin m) {
        repo.save(m);
    }

    public void borrarMonopatin(Integer id) {
        repo.deleteById(id);
    }

    public void proxParada(Integer id, Parada p) {
        repo.findById(id).ifPresent(m -> m.setParada(p));
    }

    public MonopatinDTO agregarMantenimiento(Integer id) {
        Monopatin m = repo.findById(id).orElse(null);
        MonopatinDTO rsp = null;
        if(m != null) {
            m.setDisponible(false);
            m.setMantenimiento(true);
            repo.save(m);
            rsp = new MonopatinDTO(m.getId(), m.isDisponible());
        }
        return rsp;
    }

    public MonopatinDTO quitarMantenimiento(Integer id) {
        Monopatin m = repo.findById(id).orElse(null);
        MonopatinDTO rsp = null;
        if(m != null) {
            m.setDisponible(true);
            m.setMantenimiento(false);
            repo.save(m);
            rsp = new MonopatinDTO(m.getId(), m.isDisponible());
        }
        return rsp;
    }

    public List<MonopatinDTO> listarTodosMantenimiento() {
        return repo.findMantenimiento();
    }

    public String monopatinesDisponiblesVsMant() {
        Integer disp = repo.getCantMonopatinesDisp();
        Integer mant = repo.getCantMonopatinesMant();
        return "Monopatines\n" +
                "Cant disponibles: "+disp+"\n" +
                "Cant mantenimiento: "+mant;
    }

    public List<MonopatinDTO> reporteTiempoConPausa() {
        List<Monopatin> lista = repo.findAll();
        List<MonopatinDTO> rsp = new ArrayList<>();
        for(Monopatin m : lista) {
            rsp.add(new MonopatinDTO(m.isDisponible(), m.getMinutos_uso(), m.getMinutos_pausa()));
        }
        return rsp;
    }

    public List<MonopatinDTO> reporteTiempoSinPausa() {
        List<Monopatin> lista = repo.findAll();
        List<MonopatinDTO> rsp = new ArrayList<>();
        for(Monopatin m : lista) {
            rsp.add(new MonopatinDTO(m.isDisponible(), m.getMinutos_uso(), 0));
        }
        return rsp;
    }

    public List<MonopatinDTO> reporteKm() {
        List<Monopatin> lista = repo.findAll();
        List<MonopatinDTO> rsp = new ArrayList<>();
        for(Monopatin m : lista) {
            rsp.add(new MonopatinDTO(m.isDisponible(), m.getKm_uso()));
        }
        return rsp;
    }

    public List<MonopatinDTO> monopatinesCercanos(Integer x, Integer y) {
        return repo.monopatinesCercanos(x-10, y-10, x+10, y+10);
    }

    public ParadaDTO getP(Integer id) {
        ParadasFC repoParada = null;
        Monopatin m = repo.findById(id).orElse(null);
        return repoParada.listarUna(m);
    }
}
