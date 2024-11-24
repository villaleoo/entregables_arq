package ApiGateway.config;

import ApiGateway.security.AuthotityConstant;
import ApiGateway.security.jwt.JwtFilter;
import ApiGateway.security.jwt.TokenProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/cuentas").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/viajes").hasAuthority(AuthotityConstant._CLIENTE)//el orden va de más específica a menos específica
                        .requestMatchers(HttpMethod.POST, "/api/paradas").hasAuthority(AuthotityConstant._ADMIN)//el orden va de más específica a menos específica
                        .requestMatchers(HttpMethod.POST, "/api/monopatines").hasAuthority(AuthotityConstant._ADMIN)//el orden va de más específica a menos específica
                        .requestMatchers(HttpMethod.POST, "/api/tarifas").hasAuthority(AuthotityConstant._ADMIN)//el orden va de más específica a menos específica
                        .requestMatchers(HttpMethod.POST, "/api/pausa").hasAuthority(AuthotityConstant._CLIENTE)
                        .requestMatchers(HttpMethod.POST, "/api/cuentasmp").hasAuthority(AuthotityConstant._CLIENTE)
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").hasAuthority(AuthotityConstant._CLIENTE)

                        .requestMatchers("/api/cuentas/**").hasAnyAuthority(AuthotityConstant._ADMIN, AuthotityConstant._CLIENTE) // ésta es menos específica que la de arriba
                        .requestMatchers("/api/usuarios/**").hasAuthority(AuthotityConstant._ADMIN) // ésta es menos específica que la de arriba
                        .requestMatchers("/api/viajes/**").hasAuthority(AuthotityConstant._CLIENTE)
                        .requestMatchers("/api/paradas/**").hasAuthority(AuthotityConstant._CLIENTE).
                        requestMatchers("/api/monopatines/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers("/api/tarifas/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers("/api/pausas/**").hasAuthority(AuthotityConstant._CLIENTE)
                        .requestMatchers("/api/cuentasmp/**").hasAuthority(AuthotityConstant._CLIENTE)
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtFilter(this.tokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
