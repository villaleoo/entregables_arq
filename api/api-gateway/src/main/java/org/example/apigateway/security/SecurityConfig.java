package org.example.apigateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
@Order(1)
public class SecurityConfig {
    private final JwtProvider tokenProvider;

    public SecurityConfig( JwtProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http
                .csrf( AbstractHttpConfigurer::disable );
        http
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http

                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/viajes/**").hasAnyAuthority(Constants.client,Constants.admin)
                        .requestMatchers(HttpMethod.GET,"/paradas/**").hasAnyAuthority(Constants.client,Constants.admin)

                        .requestMatchers(HttpMethod.POST,"/usuarios/**").hasAnyAuthority(Constants.client,Constants.admin)
                        .requestMatchers(HttpMethod.PUT,"/usuarios/**").hasAnyAuthority(Constants.client,Constants.admin)

                        .requestMatchers(HttpMethod.GET,"/monopatines/**").hasAnyAuthority(Constants.support,Constants.admin)
                        .requestMatchers(HttpMethod.PUT,"/monopatines/disponibilidad/**").hasAnyAuthority(Constants.support,Constants.admin)

                        .anyRequest().hasAnyAuthority(Constants.admin)
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }
}
