package utils;

import JPARepository.RepositoryCarrera;
import JPARepository.RepositoryEstudiante;
import JPARepository.RepositoryInscripcion;
import model.Carrera;
import model.Estudiante;
import model.Inscripcion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import utils.enums.Facultad;
import utils.enums.Genero;

import javax.persistence.EntityManager;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Helper {

    private static Iterable<CSVRecord> getData(String archivo) throws IOException {
        ClassLoader classLoader = Helper.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(archivo);

        if (inputStream == null) {
            throw new IOException("Archivo no encontrado: " + archivo);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(reader);

        return csvParser.getRecords();
    }

    public static void insertDefaultData (EntityManager em) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            for (CSVRecord row : getData("carreras.csv")) {
                if (row.size() >= 1) {
                    String nombreCarrera = row.get(0);
                    Facultad facultad = Facultad.fromString(row.get(1));

                    if (!nombreCarrera.isEmpty()) {
                        try {
                            Carrera carrera = new Carrera(nombreCarrera,facultad);
                            RepositoryCarrera.getInstance(em).persist(carrera);

                        } catch (NumberFormatException error) {
                            System.err.println("Error de formato en datos de carrera: " + error.getMessage());
                        }
                    }
                }
            }
            for (CSVRecord row : getData("estudiantes.csv")) {
                if (row.size() >= 7) {
                    String nombre = row.get(0);
                    String apellido = row.get(1);
                    LocalDate fecha_nac = LocalDate.parse(row.get(2),formatter);
                    Genero genero = Genero.fromString(row.get(3));
                    String documento = row.get(4);
                    String ciudad = row.get(5);
                    String nroLibreta = row.get(6);

                    if (!nombre.isEmpty() && !apellido.isEmpty() && genero!=null && !documento.isEmpty() && !ciudad.isEmpty() && !nroLibreta.isEmpty()) {
                        try {
                            int dni = Integer.parseInt(documento);
                            Estudiante estudiante = new Estudiante(nombre,apellido,fecha_nac,genero,dni,ciudad,nroLibreta);
                            RepositoryEstudiante.getInstance(em).persist(estudiante);

                        } catch (NumberFormatException error) {
                            System.err.println("Error de formato en datos de estudiante: " + error.getMessage());
                        }
                    }
                }
            }

            for (CSVRecord row : getData("inscripciones.csv")) {
                if (row.size() >= 4) {
                    Estudiante e = RepositoryEstudiante.getInstance(em).findById(Integer.parseInt(row.get(0)));
                    Carrera c = RepositoryCarrera.getInstance(em).findById(Integer.parseInt(row.get(1)));
                    LocalDate fecha_inscrip = LocalDate.parse(row.get(2),formatter);
                    LocalDate fecha_egreso= Objects.equals(row.get(3), "null") ? null : LocalDate.parse(row.get(3),formatter);

                    if ( e != null && c !=null) {
                        try {
                            Inscripcion inscripcion = new Inscripcion(e,c,fecha_inscrip,fecha_egreso);
                            RepositoryInscripcion.getInstance(em).persist(inscripcion);

                        } catch (NumberFormatException error) {
                            System.err.println("Error de formato en datos de Inscripcion: " + error.getMessage());
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
