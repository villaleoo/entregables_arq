package Gestion_servicio.Service;

import Gestion_servicio.Dto.TarifaDTO;
import Gestion_servicio.Entity.Tarifa;
import Gestion_servicio.Repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarifaService {
    @Autowired
    private TarifaRepository repo;

    public TarifaDTO nuevaTarifa(Tarifa t) {
        repo.save(t);
        return new TarifaDTO(t.getId(), t.getNormal(), t.getExtra(), t.getFecha());
    }
}
