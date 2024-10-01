import Entities.Carrera;
import Entities.Estudiante;
import Entities.Inscripcion;
import JPARepository.RepositoryCarrera;
import JPARepository.RepositoryEstudiante;
import JPARepository.RepositoryInscripcion;
import Service.ServiceCarrera;
import Service.ServiceEstudiante;
import Service.ServiceInscripcion;
import utils.enums.Facultad;
import utils.enums.Genero;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mySQLPersistance");
    EntityManager em = emf.createEntityManager();

    RepositoryCarrera repoCarrera = RepositoryCarrera.getInstance(em);
    RepositoryEstudiante repoEstudiante = RepositoryEstudiante.getInstance(em);
    RepositoryInscripcion repoInscripcion = RepositoryInscripcion.getInstance(em);

    ServiceCarrera serviceCarrera = new ServiceCarrera(repoCarrera);
    ServiceEstudiante serviceEstudiante = new ServiceEstudiante(repoEstudiante);
    ServiceInscripcion serviceInscripcion = new ServiceInscripcion(repoInscripcion, repoCarrera, repoEstudiante);

    Estudiante e1 = new Estudiante("Pedro", "Perez", LocalDate.of(1994, 8, 3), Genero.MASCULINO, 39564322, "tandil", "678rt");

    Estudiante e2 = new Estudiante("Julian", "Sanchez", LocalDate.of(1998, 3, 2), Genero.MASCULINO, 56778993, "tandil", "663if");

    Estudiante e3 = new Estudiante("Federico", "Gomez", LocalDate.of(2000, 3, 9), Genero.OTRO, 51079564, "tandil", "690jy");

    Estudiante e4 = new Estudiante("Gabriela", "figueroa", LocalDate.of(1997, 12, 10), Genero.FEMENINO, 404040404, "tandil", "388kj");

    Estudiante e5 = new Estudiante("Juliana", "ortiz", LocalDate.of(1993, 3, 12), Genero.FEMENINO, 45067894, "tandil", "889tt");

    Estudiante e6 = new Estudiante("Agustina", "hernandez", LocalDate.of(1998, 7, 7), Genero.FEMENINO, 41210677, "tandil", "776hyg");

    Carrera c1 = new Carrera("Tecnicatura universitaria en desarrollo de aplicaciones informaticas", Facultad.EXACTAS);
    Carrera c2 = new Carrera("Ingenieria en sistemas", Facultad.EXACTAS);
    Carrera c3 = new Carrera("Licenciatura en administracion de empresas", Facultad.ECONOMICAS);
    Carrera c4 = new Carrera("Medicina veterinaria", Facultad.VETERINARIAS);


    Inscripcion i1 = new Inscripcion(e1, c1);
    i1.setFecha_inscripcion(LocalDate.of(2020, 3, 3));
    i1.setFecha_egreso(LocalDate.of(2024, 6, 12));

    Inscripcion i2 = new Inscripcion(e1, c2);
    i2.setFecha_inscripcion(LocalDate.of(2020, 3, 3));

    Inscripcion i3 = new Inscripcion(e2, c3);
    i3.setFecha_inscripcion(LocalDate.of(2021, 3, 3));
    i3.setFecha_egreso(LocalDate.of(2023, 12, 12));

    Inscripcion i4 = new Inscripcion(e3, c2);
    i4.setFecha_inscripcion(LocalDate.of(2017, 3, 3));
    i4.setFecha_egreso(LocalDate.of(2022, 12, 12));

    Inscripcion i5 = new Inscripcion(e3, c1);

    Inscripcion i6 = new Inscripcion(e4, c3);
    i6.setFecha_inscripcion(LocalDate.of(2019, 3, 3));
    i6.setFecha_egreso(LocalDate.of(2024, 3, 3));

    Inscripcion i7 = new Inscripcion(e5, c4);
    i7.setFecha_inscripcion(LocalDate.of(2018, 3, 3));
    i7.setFecha_egreso(LocalDate.of(2023, 12, 12));

    Inscripcion i8 = new Inscripcion(e6, c4);
    i8.setFecha_inscripcion(LocalDate.of(2015, 3, 3));

    serviceCarrera.insert(c1);
    serviceCarrera.insert(c2);
    serviceCarrera.insert(c3);
    serviceCarrera.insert(c4);

    serviceEstudiante.insert(e1);
    serviceEstudiante.insert(e2);
    serviceEstudiante.insert(e3);
    serviceEstudiante.insert(e4);
    serviceEstudiante.insert(e5);
    serviceEstudiante.insert(e6);

    serviceInscripcion.insert(i1);
    serviceInscripcion.insert(i2);
    serviceInscripcion.insert(i3);
    serviceInscripcion.insert(i4);
    serviceInscripcion.insert(i5);
    serviceInscripcion.insert(i6);
    serviceInscripcion.insert(i7);
    serviceInscripcion.insert(i8);

    List<Estudiante> estudiantesOrdenadosAlfabeticamente = serviceEstudiante.getAllAlphabetically();
    System.out.println("\t TOTALIDAD DE ESTUDIANTES ORDENADOS POR APELLIDO ALFABETICAMENTE: \n");
    for(Estudiante eAlf : estudiantesOrdenadosAlfabeticamente){
        System.out.println(eAlf);
    }

    /*D) */
    String num_libreta="388kj";

    Estudiante eLib = serviceEstudiante.getByNumLibreta(num_libreta);
    System.out.println("\n \tESTUDIANTE CON NUMERO DE LIBRETA "+num_libreta+":\n");
    if(eLib != null){
        System.out.println(eLib);
    }else{
        System.out.println("No hay estudiante con numero de libreta: "+num_libreta+". Intente con uno valido.");
    }

    /*E) */
    List<Estudiante> estudiantesFemeninos= serviceEstudiante.getAllByGenero(Genero.FEMENINO);
    System.out.println("\n \tTOTALIDAD DE ESTUDIANTES FEMENINOS: \n");

    for(Estudiante eFem : estudiantesFemeninos){
        System.out.println(eFem);
    }

    List<Estudiante> estudiantesMasculinos= serviceEstudiante.getAllByGenero(Genero.MASCULINO);
    System.out.println("\n \tTOTALIDAD DE ESTUDIANTES MASCULINOS: \n");

    for(Estudiante eMasc : estudiantesMasculinos){
        System.out.println(eMasc);
    }

    /*F) */
    List<Carrera> carreras_con_inscriptos= serviceCarrera.getAllOrderEnrolled();
    System.out.println("\n \tCARRERAS CON ESTUDIANTES INSCRIPTOS DE MAYOR A MENOR INSCRIPTOS: \n");

    for(Carrera carrIn : carreras_con_inscriptos){
        System.out.println(carrIn);
    }

    /*G) */
    Integer id_carrera= 1;
    List<Estudiante> estudiantes_por_carrera_ciudad= serviceEstudiante.getAllByCiudadAndCarrera("tandil",id_carrera);
    System.out.println("\n \tESTUDIANTES QUE RESIDEN EN TANDIL Y ESTUDIAN LA CARRERA CON ID="+id_carrera+"" +"\n");

    for(Estudiante est_ciudad : estudiantes_por_carrera_ciudad){
        System.out.println(est_ciudad);
    }

    /*3) */
    serviceCarrera.showReport();

}
