package Integrador2.Utils;

import Integrador2.Entities.Carrera;
import Integrador2.Entities.Estudiante;
import Integrador2.Repository.CarreraRepository;
import Integrador2.Repository.EstudianteRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class HelperInitDB {

    public HelperInitDB() {
    }
    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void insertDefaultData(EntityManager em) throws Exception {
        try {
            for (CSVRecord row : getData("Carreras.csv")) {
                if (row.size() >= 1) {
                    String nombreCarrera = row.get(0);

                    if (!nombreCarrera.isEmpty()) {
                        try {
                            Carrera carrera = new Carrera(nombreCarrera);
                            CarreraRepository.getInstance(em).persist(carrera);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de carrera: " + e.getMessage());
                        }
                    }
                }
            }
            for (CSVRecord row : getData("Estudiantes.csv")) {
                if (row.size() >= 7) {
                    String nombre = row.get(0);
                    String apellido = row.get(1);
                    String edad = row.get(2);
                    String genero = row.get(3);
                    String documento = row.get(4);
                    String ciudad = row.get(5);
                    String nroLibreta = row.get(6);

                    if (!nombre.isEmpty() && !apellido.isEmpty()
                            && !edad.isEmpty() && !genero.isEmpty() && !documento.isEmpty()
                            && !ciudad.isEmpty() && !nroLibreta.isEmpty()) {
                        try {
                            int age = Integer.parseInt(edad);
                            int dni = Integer.parseInt(documento);
                            int libreta = Integer.parseInt(nroLibreta);
                            Estudiante estudiante = new Estudiante(nombre, apellido, age, genero, dni, ciudad, libreta);
                            EstudianteRepository.getInstance(em).persist(estudiante);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de estudiante: " + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
