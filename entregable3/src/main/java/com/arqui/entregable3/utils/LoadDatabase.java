package com.arqui.entregable3.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arqui.entregable3.repository.EstudianteRepository;
import com.arqui.entregable3.repository.InscripcionRepository;
import com.arqui.entregable3.utils.enums.Genero;
import com.arqui.entregable3.model.Entities.Carrera;
import com.arqui.entregable3.model.Entities.Estudiante;
import com.arqui.entregable3.model.Entities.Inscripcion;
import com.arqui.entregable3.repository.CarreraRepository;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDataBase(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository,
            InscripcionRepository inscripcionRepository) {
        return args -> {
            /* 
            // Crear el estudiante
            Estudiante estudiante1 = new Estudiante("Esteban", "Orellano", LocalDate.of(2002, 11, 24), Genero.MASCULINO,
                    421422, "Rauch", "asdasdsa2");

            // Guardar el estudiante en la base de datos
            log.info("Preloading " + estudianteRepository.save(estudiante1));

            // Crear la carrera
            Carrera carrera1 = new Carrera("produccion");
            log.info("Preloading " + carreraRepository.save(carrera1));

            // Crear la inscripción
            Inscripcion insc = new Inscripcion(estudiante1, carrera1);
            log.info("Preloading " + inscripcionRepository.save(insc));
            */
        };
    }     
}
