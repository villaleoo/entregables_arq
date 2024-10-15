package com.arqui.entregable3.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.arqui.entregable3.repository.EstudianteRepository;
import com.arqui.entregable3.repository.InscripcionRepository;
import com.arqui.entregable3.repository.CarreraRepository;


@Configuration
@Slf4j
class LoadDatabase {

    
    @Bean
    CommandLineRunner initDataBase(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository,
            InscripcionRepository inscripcionRepository) {
        return args -> {


        };
    }
}
