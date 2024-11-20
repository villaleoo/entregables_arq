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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

                        .requestMatchers(HttpMethod.PUT,"/monopatines/ubicacion/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/cuentas/recargar/**").authenticated()

                        .requestMatchers(HttpMethod.POST,"/usuarios/**").hasAnyAuthority(Constants.client,Constants.admin)
                        .requestMatchers(HttpMethod.PUT,"/usuarios/**").hasAnyAuthority(Constants.client,Constants.admin)


                        .requestMatchers(HttpMethod.POST,"/viajes/**").hasAnyAuthority(Constants.client,Constants.admin)
                        .requestMatchers(HttpMethod.PUT,"/viajes/**").hasAnyAuthority(Constants.client,Constants.admin)

                        .requestMatchers(HttpMethod.PUT,"/monopatines/**").hasAnyAuthority(Constants.support,Constants.admin)


                        .requestMatchers(HttpMethod.GET,"/cuentas/**").authenticated() /*esto deberia estar prohibido para quienes no sean admin pero hay que configurar tokens entre microservicios*/

                        .requestMatchers(HttpMethod.GET,"/paradas/cercanas").authenticated()



                        .anyRequest().hasAnyAuthority(Constants.admin)
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Authorization");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
