package Gestion_servicio.Service;


import Gestion_servicio.Dto.MonopatinDTO;
import Gestion_servicio.Dto.ViajeDTO;
import Gestion_servicio.Entity.Tarifa;
import Gestion_servicio.Entity.Viaje;
import Gestion_servicio.FeignClient.MonopatinFC;
import Gestion_servicio.Repository.TarifaRepository;
import Gestion_servicio.Repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository repo;
    @Autowired
    private TarifaRepository tarifaRepo;
    private MonopatinFC monoRepo;

    public void nuevoViaje(Viaje v) {
        Tarifa t = tarifaRepo.getUltimaTarifa();
        v.setTarifa(t);
        repo.save(v);
    }

    public List<ViajeDTO> traerViajes() {
        List<Viaje> viajes = repo.findAll();
        List<ViajeDTO> rsp = new ArrayList<>();
        for(Viaje v : viajes) {
            rsp.add(new ViajeDTO());
        }
        return rsp;
    }

    public void finViaje(Integer id, Integer tiempo, Integer km) {

    }

    public List<MonopatinDTO> monopatinesPorAnio(Integer viajes, Integer anio) {
        List<Integer> m = repo.monopatinesPorAnio(viajes, anio);
        List<MonopatinDTO> rsp = new ArrayList<>();
        for(Integer id : m) {
            rsp.add(monoRepo.listarUno(id));
        }
        return rsp;
    }

    public Integer totalFacturadoEnAnioEntreMeses(Integer anio, Integer inicio, Integer fin) {
        return repo.totalFacturadoEnAnioEntreMeses(anio, inicio, fin);
    }

    public String getCantMonopatines() {
        return monoRepo.monopatinesDisponiblesVsMant();
    }
}
