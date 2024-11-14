package MicroservicioUsuario.Service;

import MicroservicioUsuario.DTO.CuentaDTO;
import MicroservicioUsuario.Entities.Cuenta;
import MicroservicioUsuario.Repository.CuentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public void add(CuentaDTO cuenta){
        Cuenta c = new Cuenta();
        c.setFechaAlta(cuenta.getFechaAlta());
        c.setCredito(cuenta.getCredito());
        cuentaRepository.save(c);
    }

    public CuentaDTO getById(Long id) {
        Cuenta c = cuentaRepository.findById(id).orElse(null);
        if (c != null)
            return new CuentaDTO(c.getFechaAlta(), c.getCredito(),c.getHabilitada());
        throw new EntityNotFoundException("No se encontro cuenta  con id: " + id);
    }

    public Page<CuentaDTO> getAll(Pageable pageable) {
        Page<Cuenta> cuentas = cuentaRepository.findAll(pageable);
        List<CuentaDTO> res = new LinkedList<>();

        for (Cuenta c : cuentas)
            res.add(new CuentaDTO(c.getFechaAlta(), c.getCredito(), c.getHabilitada()));

        return new PageImpl<>(res, pageable, cuentas.getTotalElements());
    }

    public void delete(Long id) {
        if (!cuentaRepository.existsById(id))
            throw new EntityNotFoundException("Cuenta no encontrada con id: " + id);
        cuentaRepository.deleteById(id);
    }

    public Cuenta update(Long id, CuentaDTO cuenta) {
        if (!cuentaRepository.existsById(id))
            throw new EntityNotFoundException("Cuenta no encontrada con id: " + id);
        Cuenta c = cuentaRepository.findById(id).orElse(null);
        c.setFechaAlta(cuenta.getFechaAlta());
        c.setCredito(cuenta.getCredito());
        c.setHabilitada(cuenta.getHabilitada());
        cuentaRepository.save(c);
        return c;
    }
}
