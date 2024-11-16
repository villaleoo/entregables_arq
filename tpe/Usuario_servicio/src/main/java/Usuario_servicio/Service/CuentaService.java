package Usuario_servicio.Service;

import Usuario_servicio.Dto.CuentaDTO;
import Usuario_servicio.Dto.UsuarioDTO;
import Usuario_servicio.Entity.Cuenta;
import Usuario_servicio.Entity.Usuario;
import Usuario_servicio.Repository.CuentaRepository;
import Usuario_servicio.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository r;
    @Autowired
    private UsuarioRepository userRepo;

    public List<CuentaDTO> getCuentas() {
        return r.traerCuentas();
    }
    public CuentaDTO getCuenta(Integer id) {
        CuentaDTO rsp = r.traerUnaCuenta(id);

        return r.traerUnaCuenta(id);
    }

    public void bajarCuenta(Integer id) {
        Cuenta c = r.findById(id).orElse(null);
        if(c != null) {
            c.setFecha_alta("Fuera de servicio");
            r.save(c);
        }
    }
    public void habilitarCuenta(Integer id) {
        Cuenta c = r.findById(id).orElse(null);
        if(c != null) {
            c.setFecha_alta(new Date().toString());
            r.save(c);
        }
    }

    public void nuevaCuenta(Cuenta c) {
        r.save(c);
    }

    public List<UsuarioDTO> getUsuariosDeCuenta(Integer id) {
        return userRepo.traerUsuariosDeCuenta(id);
    }
}
