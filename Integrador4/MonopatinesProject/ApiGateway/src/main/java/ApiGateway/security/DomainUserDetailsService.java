package ApiGateway.security;

import ApiGateway.Entities.Rol;
import ApiGateway.Entities.Cuenta;
import ApiGateway.repository.CuentaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final CuentaRepository userRepository;

    public DomainUserDetailsService(CuentaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);

        return userRepository
                .findOneWithAuthoritiesByEmailIgnoreCase(email.toLowerCase())
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));
    }

    private UserDetails createSpringSecurityUser(Cuenta user) {
        List<GrantedAuthority> grantedAuthorities = user
                .getRoles()
                .stream()
                .map(Rol::getTipo)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}
