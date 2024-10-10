package org.example.integrador_3.utils;

import org.example.integrador_3.persistencia.modelo.Estudiante;
import org.example.integrador_3.repositorio.EstudianteRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CargarDatos {
    @Bean
    CommandLineRunner initDatabase(@Qualifier("estudianteRepo") EstudianteRepo estudiante) {
        return args -> {
            estudiante.save(new Estudiante(1, "Ulises", "Cortes", 26, "masculino", 12345, "Tandil", 1));
            estudiante.save(new Estudiante(2, "Jorge", "Perez", 30, "masculino", 12346, "Buenos Aires", 2));
            estudiante.save(new Estudiante(3, "Jorgelina", "Rodriguez", 60, "femenino", 12347, "Azul", 3));
            estudiante.save(new Estudiante(4, "Maria", "Haaland", 47, "femenino", 12345, "Resistencia", 4));
        };
    }
}
