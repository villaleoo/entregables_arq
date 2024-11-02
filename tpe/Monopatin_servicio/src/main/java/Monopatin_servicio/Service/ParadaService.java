package Monopatin_servicio.Service;

import Monopatin_servicio.Dto.ParadaDTO;
import Monopatin_servicio.Entity.Parada;
import Monopatin_servicio.Repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParadaService {
    @Autowired
    private ParadaRepository repo;

    public List<ParadaDTO> listarTodas() {
        List<Parada> lista = repo.findAll();
        List<ParadaDTO> rsp = new ArrayList<>();
        for(Parada p : lista) {
            rsp.add(new ParadaDTO(p.getId(), p.getCiudad(), p.getDireccion()));
        }
        return rsp;
    }

    public ParadaDTO listarUna(Integer id) {
        Parada p = repo.findById(id).orElse(null);
        if(p != null) {
            return new ParadaDTO(p.getId(), p.getCiudad(), p.getDireccion());
        }
        return null;
    }

    public void registrar(Parada p) {
        repo.save(p);
    }

    public void borrarParada(Integer id) {
        repo.deleteById(id);
    }
}
