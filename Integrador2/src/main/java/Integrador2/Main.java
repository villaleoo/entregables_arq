package Integrador2;

import Integrador2.Entities.Estudiante;
import Integrador2.Repository.CarreraRepository;
import Integrador2.Repository.InscripcionRepository;
import Integrador2.Repository.EstudianteRepository;
import Integrador2.Utils.HelperMySQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
        EntityManager em = emf.createEntityManager();
        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.insertDefaultData(em);


        EstudianteRepository estudianteRepository = EstudianteRepository.getInstance(em);
        CarreraRepository carreraRepository = CarreraRepository.getInstance(em);
        InscripcionRepository inscripcionRepository = InscripcionRepository.getInstance(em);
        em.getTransaction().begin();

        //"2)A) dar de alta un estudiante"
        estudianteRepository.persist(new Estudiante("Juan", "Lopez", 32, "Masculino", 323213, "Buenos Aires", 2312));

        //2)B "matricular un estudiante en una carrera"
        inscripcionRepository.matricularEstudiante(4343432, "TUDAI", LocalDate.of(2021, 5, 12));
        inscripcionRepository.matricularEstudiante(4351123, "TUARI", LocalDate.of(2020, 7, 22));
        inscripcionRepository.matricularEstudiante(4351234, "Ingeniería en Sistemas", LocalDate.of(2022, 9, 15));
        inscripcionRepository.matricularEstudiante(4351345, "Ciencias de la Computación", LocalDate.of(2023, 11, 3));

        inscripcionRepository.matricularEstudiante(4351456, "TUDAI", LocalDate.of(2020, 3, 18));
        inscripcionRepository.matricularEstudiante(4351567, "TUARI", LocalDate.of(2021, 12, 1));
        inscripcionRepository.matricularEstudiante(4351678, "Ingeniería en Sistemas", LocalDate.of(2023, 8, 20));
        inscripcionRepository.matricularEstudiante(4351789, "Ciencias de la Computación", LocalDate.of(2021, 4, 27));

        inscripcionRepository.matricularEstudiante(4351890, "TUDAI", LocalDate.of(2022, 10, 14));
        inscripcionRepository.matricularEstudiante(4351901, "TUARI", LocalDate.of(2020, 6, 9));
        inscripcionRepository.matricularEstudiante(4352012, "Ingeniería en Sistemas", LocalDate.of(2021, 11, 7));
        inscripcionRepository.matricularEstudiante(4352123, "Ciencias de la Computación", LocalDate.of(2023, 1, 23));

        inscripcionRepository.matricularEstudiante(4352234, "TUDAI", LocalDate.of(2020, 2, 28));
        inscripcionRepository.matricularEstudiante(4352345, "TUARI", LocalDate.of(2022, 12, 19));
        inscripcionRepository.matricularEstudiante(4352456, "Ingeniería en Sistemas", LocalDate.of(2021, 8, 5));
        inscripcionRepository.matricularEstudiante(4352567, "Ciencias de la Computación", LocalDate.of(2023, 7, 13));

        inscripcionRepository.matricularEstudiante(4352678, "TUDAI", LocalDate.of(2021, 6, 21));
        inscripcionRepository.matricularEstudiante(4352789, "TUARI", LocalDate.of(2020, 9, 30));
        inscripcionRepository.matricularEstudiante(4352890, "Ingeniería en Sistemas", LocalDate.of(2022, 4, 11));
        inscripcionRepository.matricularEstudiante(4352901, "Ciencias de la Computación", LocalDate.of(2023, 3, 16));

        inscripcionRepository.matricularEstudiante(4353012, "TUDAI", LocalDate.of(2020, 11, 25));
        inscripcionRepository.matricularEstudiante(4353123, "TUARI", LocalDate.of(2022, 5, 17));
        inscripcionRepository.matricularEstudiante(4353234, "Ingeniería en Sistemas", LocalDate.of(2021, 2, 14));
        inscripcionRepository.matricularEstudiante(4353345, "Ciencias de la Computación", LocalDate.of(2023, 9, 6));

        inscripcionRepository.matricularEstudiante(4353456, "TUDAI", LocalDate.of(2021, 10, 31));
        inscripcionRepository.matricularEstudiante(4353567, "TUARI", LocalDate.of(2023, 2, 20));
        inscripcionRepository.matricularEstudiante(4353678, "Ingeniería en Sistemas", LocalDate.of(2022, 1, 9));
        inscripcionRepository.matricularEstudiante(4353789, "Ciencias de la Computación", LocalDate.of(2020, 8, 3));

        inscripcionRepository.matricularEstudiante(4353890, "TUDAI", LocalDate.of(2023, 6, 12));
        inscripcionRepository.matricularEstudiante(4353901, "TUARI", LocalDate.of(2020, 4, 19));
        inscripcionRepository.matricularEstudiante(4354012, "Ingeniería en Sistemas", LocalDate.of(2021, 12, 5));
        inscripcionRepository.matricularEstudiante(4354123, "Ciencias de la Computación", LocalDate.of(2022, 7, 24));

        inscripcionRepository.matricularEstudiante(4354234, "TUDAI", LocalDate.of(2020, 1, 26));
        inscripcionRepository.matricularEstudiante(4354345, "TUARI", LocalDate.of(2021, 5, 3));
        inscripcionRepository.matricularEstudiante(4354456, "Ingeniería en Sistemas", LocalDate.of(2023, 11, 8));
        inscripcionRepository.matricularEstudiante(4354567, "Ciencias de la Computación", LocalDate.of(2022, 10, 30));

        inscripcionRepository.matricularEstudiante(4354678, "TUDAI", LocalDate.of(2020, 3, 13));
        inscripcionRepository.matricularEstudiante(4354789, "TUARI", LocalDate.of(2021, 9, 19));
        inscripcionRepository.matricularEstudiante(4354890, "Ingeniería en Sistemas", LocalDate.of(2023, 4, 27));
        inscripcionRepository.matricularEstudiante(4354901, "Ciencias de la Computación", LocalDate.of(2022, 11, 16));

        inscripcionRepository.matricularEstudiante(4355012, "TUDAI", LocalDate.of(2020, 6, 25));
        inscripcionRepository.matricularEstudiante(4355123, "TUARI", LocalDate.of(2021, 8, 22));
        inscripcionRepository.matricularEstudiante(4355234, "Ingeniería en Sistemas", LocalDate.of(2023, 12, 2));
        inscripcionRepository.matricularEstudiante(4355345, "Ciencias de la Computación", LocalDate.of(2022, 3, 7));

        inscripcionRepository.matricularEstudiante(4355456, "TUDAI", LocalDate.of(2020, 2, 15));
        inscripcionRepository.matricularEstudiante(4355567, "TUARI", LocalDate.of(2021, 11, 9));
        inscripcionRepository.matricularEstudiante(4355678, "Ingeniería en Sistemas", LocalDate.of(2023, 5, 29));


        System.out.println("2)C) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple. ");
        System.out.println(estudianteRepository.getEstudiantesOrderedBy("nombre"));

        System.out.println("\n//////////////////////////////////////////");
        System.out.println("//////////////////////////////////////////\n");

        System.out.println("2)D) recuperar un estudiante, en base a su número de libreta universitaria ");
        System.out.println(estudianteRepository.getEstudianteByNroLibreta(250418));

        System.out.println("\n//////////////////////////////////////////");
        System.out.println("//////////////////////////////////////////\n");

        System.out.println("2)E) recuperar todos los estudiantes, en base a su género. ");
        System.out.println(estudianteRepository.getEstudiantesByGenero("Femenino"));

        System.out.println("\n//////////////////////////////////////////");
        System.out.println("//////////////////////////////////////////\n");

        System.out.println("2)F) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos. ");
        System.out.println(carreraRepository.getCarrerasConEstudiantesInscriptos());

        System.out.println("\n//////////////////////////////////////////");
        System.out.println("//////////////////////////////////////////\n");

        System.out.println("2)G) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia. ");
        System.out.println(estudianteRepository.getEstudiantesByCarreraAndCiudad("TUDAI", "Buenos Aires"));

        System.out.println("\n//////////////////////////////////////////");
        System.out.println("//////////////////////////////////////////\n");

        System.out.println("3) Generar un reporte de las carreras, que para cada carrera incluya información de los\n" + "inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar\n" + "los años de manera cronológica.");

        System.out.println(inscripcionRepository.getReporte());

        em.getTransaction().commit();
        em.close();
    }
}