package com.gateway.api_gateway.config;

import com.gateway.api_gateway.Repository.UserRepository;
import com.gateway.api_gateway.Security.AuthotityConstant;
import com.gateway.api_gateway.Security.jwt.JwtFilter;
import com.gateway.api_gateway.Security.jwt.TokenProvider;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
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

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public UserRepository userRepository(EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager).getRepository(UserRepository.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http ) throws Exception {
        http
                .csrf( AbstractHttpConfigurer::disable );
        http
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
                .securityMatcher("/**" )
                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/estaciones").permitAll()
                        .requestMatchers(HttpMethod.POST, "/monopatines").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cuentas").permitAll()
                        .requestMatchers(HttpMethod.POST, "/viajes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers( HttpMethod.POST,"/api/carreras").hasAuthority( AuthotityConstant._ADMIN )//el orden va de más específica a menos específica
                        .requestMatchers( "/api/carreras/**").hasAuthority( AuthotityConstant._ALUMNO ) // ésta es menos específica que la de arriba
                        .requestMatchers("/api/estudiantes/**").hasAuthority( AuthotityConstant._ALUMNO )
                        .requestMatchers( "/api/inscripciones/**").hasAuthority( AuthotityConstant._ADMIN )
                        .anyRequest().authenticated()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }
}
