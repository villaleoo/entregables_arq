package MicroservicioUsuario.Service;

import MicroservicioUsuario.DTO.CuentaMPDTO;
import MicroservicioUsuario.Entities.CuentaMP;
import MicroservicioUsuario.Repository.CuentaMPRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CuentaMPService {
    @Autowired
    private CuentaMPRepository cuentaRepository;

    public void add(CuentaMPDTO cuenta) {
        CuentaMP c = new CuentaMP();
        c.setCredito(cuenta.getCredito());
        cuentaRepository.save(c);
    }

    public CuentaMPDTO getById(Long id) {
        CuentaMP c = cuentaRepository.findById(id).orElse(null);
        if (c != null)
            return new CuentaMPDTO(c.getCredito());
        throw new EntityNotFoundException("No se encontro cuenta  con id: " + id);
    }

    public Page<CuentaMPDTO> getAll(Pageable pageable) {
        Page<CuentaMP> cuentas = cuentaRepository.findAll(pageable);
        List<CuentaMPDTO> res = new LinkedList<>();

        for (CuentaMP c : cuentas)
            res.add(new CuentaMPDTO(c.getCredito()));

        return new PageImpl<>(res, pageable, cuentas.getTotalElements());
    }

    public void delete(Long id) {
        if (!cuentaRepository.existsById(id))
            throw new EntityNotFoundException("Cuenta no encontrada con id: " + id);
        cuentaRepository.deleteById(id);
    }

    public CuentaMP update(Long id, CuentaMPDTO cuenta) {
        if (!cuentaRepository.existsById(id))
            throw new EntityNotFoundException("Cuenta no encontrada con id: " + id);
        CuentaMP c = cuentaRepository.findById(id).orElse(null);
        c.setCredito(cuenta.getCredito());
        cuentaRepository.save(c);
        return c;
    }
}
