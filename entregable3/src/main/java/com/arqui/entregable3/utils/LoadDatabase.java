package com.arqui.entregable3.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.arqui.entregable3.model.Entities.Estudiante;
import com.arqui.entregable3.repository.EstudianteRepository;
import java.time.LocalDate;
import com.arqui.entregable3.utils.enums.Genero;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDataBase(@Qualifier("estudianteRepository") EstudianteRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(
                    new Estudiante("Esteban", "Orellano", LocalDate.of(2002, 11, 24), Genero.MASCULINO, 44357083,
                            "Rauch", "EA24")));
            log.info("Preloading " + repository.save(
                    new Estudiante("Juan", "Peralta", LocalDate.of(2018, 12, 9), Genero.FEMENINO, 2895670,
                            "Bs As", "EA224")));
        };
    }

}
