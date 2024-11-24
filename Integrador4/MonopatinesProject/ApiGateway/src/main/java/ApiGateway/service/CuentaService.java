package ApiGateway.service;

import ApiGateway.Entities.Cuenta;
import ApiGateway.Entities.Rol;
import ApiGateway.FeignClients.UsuarioFeingClient;
import ApiGateway.repository.AuthorityRepository;
import ApiGateway.repository.CuentaRepository;
import ApiGateway.service.dto.cuentasMP.CuentaMPDTO;
import ApiGateway.service.dto.user.CuentaDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CuentaService {

    private final CuentaRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final UsuarioFeingClient usuarioFeingClient;

    public long saveUser(CuentaDTO request) {
        final var user = new Cuenta(request.getEmail());
        CuentaMPDTO cuentaMP = usuarioFeingClient.getCuentaById(request.getIdCuentaMP()).orElse(null);
        if (cuentaMP != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            final var roles = this.authorityRepository.findAllById(request.getAuthorities());
            user.setAuthorities(roles);
            user.setIdCuentaMP(request.getIdCuentaMP());
            final var result = this.userRepository.save(user);
            return result.getId();
        } else
            throw new RuntimeException("El id de la cuenta de mercado pago no existe.");
    }

    public ApiGateway.service.dto.CuentaDTO getCuentaById(Long id) {
        Cuenta cuenta = this.userRepository.findById(id).orElse(null);
        Set<String> roles = new HashSet<>();
        for (Rol r : cuenta.getRoles())
            roles.add(r.getTipo());
        return new ApiGateway.service.dto.CuentaDTO(cuenta.getEmail(), cuenta.getIdCuentaMP(), cuenta.getHabilitada(), cuenta.getFechaAlta(), roles);
    }
}
