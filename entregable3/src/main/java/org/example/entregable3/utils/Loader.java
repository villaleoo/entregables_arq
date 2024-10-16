package org.example.entregable3.utils;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.entregable3.DTO.InscripcionDTO;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.model.Inscripcion;
import org.example.entregable3.model.InscripcionId;
import org.example.entregable3.repository.CarreraRepository;
import org.example.entregable3.repository.EstudianteRepository;
import org.example.entregable3.repository.InscripcionRepository;
import org.example.entregable3.utils.enums.Facultad;
import org.example.entregable3.utils.enums.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component

public class Loader {

    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private InscripcionRepository inscripcionRepository;


    public void loadCarreras() throws IOException {
        Set<String> processedCarreras = new HashSet<>();
        Path carrerasPath = Paths.get("./src/main/resources/dataset/carreras.csv");
        try (FileReader reader = new FileReader(carrerasPath.toFile());
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                String nombreCarrera = csvRecord.get("nombreCarrera");
                if (processedCarreras.add(nombreCarrera)) {
                    Carrera c = new Carrera();
                    c.setTitulo(nombreCarrera);
                    Facultad f = Facultad.fromString(csvRecord.get("facultad"));
                    c.setFacultad(f);
                    carreraRepository.save(c);
                }
            }
        }
    }

    public void loadEstudiantes() throws IOException {
        Path estudiantesPath = Paths.get("./src/main/resources/dataset/estudiantes.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (FileReader reader = new FileReader(estudiantesPath.toFile());
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                LocalDate nac = LocalDate.parse(csvRecord.get("fecha_nacimiento"), formatter);
                Genero g = Genero.fromString(csvRecord.get("genero"));
                Integer dni = Integer.parseInt(csvRecord.get("dni"));
                estudianteRepository.save(
                        new Estudiante(
                                csvRecord.get("nombre"),
                                csvRecord.get("apellido"),
                                nac, g, dni,
                                csvRecord.get("ciudad"),
                                csvRecord.get("nroLibreta")
                        )
                );
            }
        }
    }

    public void loadInscripciones() throws IOException {
        Path inscripcionesPath = Paths.get("./src/main/resources/dataset/inscripciones.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (FileReader reader = new FileReader(inscripcionesPath.toFile());
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                LocalDate inscrip = LocalDate.parse(csvRecord.get("fecha_inscripcion"), formatter);
                LocalDate egreso = Objects.equals(csvRecord.get("fecha_egreso"), "null")
                        ? null : LocalDate.parse(csvRecord.get("fecha_egreso"), formatter);
                Optional<Estudiante> e1 = estudianteRepository.findById(Integer.parseInt(csvRecord.get("id_persona")));
                Optional<Carrera> c1 = carreraRepository.findById(Integer.parseInt(csvRecord.get("id_carrera")));
                Inscripcion i = new Inscripcion(e1.get(), c1.get(), inscrip, egreso);
                inscripcionRepository.save(i);
            }
        }
    }
}