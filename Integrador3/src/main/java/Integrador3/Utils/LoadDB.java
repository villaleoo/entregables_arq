package Integrador3.Utils;

import Integrador3.Entities.Carrera;
import Integrador3.Entities.Estudiante;
import Integrador3.Entities.Inscripcion;
import Integrador3.Entities.InscripcionID;
import Integrador3.Repository.CarreraRepository;
import Integrador3.Repository.EstudianteRepository;
import Integrador3.Repository.InscripcionRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LoadDB {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    @PostConstruct
    public void insertData() throws Exception {
        try {
            for (CSVRecord row : getData("Carreras.csv")) {
                if (row.size() >= 2)

                    carreraRepository.save(new Carrera(Long.valueOf(row.get(0)), row.get(1)));
            }
            for (CSVRecord row : getData("Estudiantes.csv")) {
                if (row.size() >= 7)
                    estudianteRepository.save(new Estudiante(Long.valueOf(row.get(0)), row.get(1), row.get(2), Integer.parseInt(row.get(3)), row.get(4),
                            row.get(5), Integer.valueOf(row.get(6))));
            }

            for (CSVRecord row : getData("Inscripciones.csv")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setId(new InscripcionID(Long.valueOf(row.get(0)), Long.valueOf(row.get(1))));
                inscripcion.setFechaInscripcion(LocalDate.parse(row.get(2), formatter));
                inscripcion.setCarrera(carreraRepository.findById(Long.valueOf(row.get(1))).orElse(null));
                inscripcion.setEstudiante(estudianteRepository.findById(Long.valueOf(row.get(0))).orElse(null));
                if (!row.get(3).equals("NULL"))
                    inscripcion.setFechaGraduacion(LocalDate.parse(row.get(3), formatter));
                else
                    inscripcion.setFechaGraduacion(null);
                inscripcionRepository.save(inscripcion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
