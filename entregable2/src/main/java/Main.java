import DTO.ReporteCarreraDTO;
import JPARepository.RepositoryCarrera;
import JPARepository.RepositoryEstudiante;
import JPARepository.RepositoryInscripcion;
import model.Carrera;
import model.Estudiante;
import model.Inscripcion;
import model.InscripcionId;
import service.ServiceCarrera;
import service.ServiceEstudiante;
import service.ServiceInscripcion;
import utils.Helper;
import utils.enums.Facultad;
import utils.enums.Genero;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public static void main (String [] args) throws Exception {

    EntityManagerFactory emf= Persistence.createEntityManagerFactory("mySQLPersistance");
    EntityManager em = emf.createEntityManager();

    Helper.insertDefaultData(em);

    RepositoryCarrera rc =RepositoryCarrera.getInstance(em);
    RepositoryEstudiante re = RepositoryEstudiante.getInstance(em);
    RepositoryInscripcion ri = RepositoryInscripcion.getInstance(em);


    ServiceCarrera servicio_carrera = new ServiceCarrera(rc);
    ServiceEstudiante servicio_estudiante = new ServiceEstudiante(re);
    ServiceInscripcion servicio_inscripcion = new ServiceInscripcion(ri,re,rc);



    //////////////////////////////////////////////SERVICIOS/////////////////////////////////////////////

    /*A)*/
    Estudiante nuevoEstudiante = servicio_estudiante.insert(new Estudiante("agustin","morales",LocalDate.of(2000,04,26),Genero.MASCULINO,44211677,"tandil","78653T"));

    System.out.println("Datos del nuevo estudiante persistido en la db : "+ nuevoEstudiante);

    /*B) */
    Inscripcion nuevaInscipcion =servicio_inscripcion.insert(new Inscripcion(servicio_estudiante.getByNumLibreta("250421"), servicio_carrera.getById(2))) ;

    System.out.println("Datos de la nueva matriculacion de un estudiante persistida en la db : " + nuevaInscipcion );

    /*C) */
    List<Estudiante> estudiantesOrdenadosAlfabeticamente = servicio_estudiante.getAllAlphabetically();
    System.out.println("\t TOTALIDAD DE ESTUDIANTES ORDENADOS POR APELLIDO ALFABETICAMENTE: \n");
    for(Estudiante eAlf : estudiantesOrdenadosAlfabeticamente){
        System.out.println(eAlf);
    }

    /*D) */
    String num_libreta="250420";

    Estudiante eLib = servicio_estudiante.getByNumLibreta(num_libreta);
    System.out.println("\n \tESTUDIANTE CON NUMERO DE LIBRETA "+num_libreta+":\n");

    if(eLib != null){
        System.out.println(eLib);
    }else{
        System.out.println("No hay estudiante con numero de libreta: "+num_libreta+". Intente con uno valido.");
    }

    /*E) */
    List<Estudiante> estudiantesFemeninos= servicio_estudiante.getAllByGenero(Genero.FEMENINO);
    System.out.println("\n \tTOTALIDAD DE ESTUDIANTES FEMENINOS: \n");

    for(Estudiante eFem : estudiantesFemeninos){
        System.out.println(eFem);
    }

    List<Estudiante> estudiantesMasculinos= servicio_estudiante.getAllByGenero(Genero.MASCULINO);
    System.out.println("\n \tTOTALIDAD DE ESTUDIANTES MASCULINOS: \n");

    for(Estudiante eMasc : estudiantesMasculinos){
        System.out.println(eMasc);
    }

    /*F) */
    List<Carrera> carreras_con_inscriptos= servicio_carrera.getAllOrderEnrolled();
    System.out.println("\n \tCARRERAS CON ESTUDIANTES INSCRIPTOS DE MAYOR A MENOR INSCRIPTOS: \n");

    for(Carrera carrIn : carreras_con_inscriptos){
        System.out.println(carrIn);
    }

    /*G) */
    Integer id_carrera= 2;
    String ciudad= "tierra del fuego";
    List<Estudiante> estudiantes_por_carrera_ciudad= servicio_estudiante.getAllByCiudadANDCarrera(ciudad,id_carrera);
    if(estudiantes_por_carrera_ciudad.isEmpty()){
        System.out.println("No hay estudiantes de "+ciudad+" que estudien la carrera con id="+id_carrera);
    }else{
        System.out.println("\n \tESTUDIANTES QUE RESIDEN EN TIERRA DEL FUEGO Y ESTUDIAN LA CARRERA CON ID="+id_carrera+"" +"\n");

        for(Estudiante est_ciudad : estudiantes_por_carrera_ciudad){
            System.out.println(est_ciudad);
        }
    }

    /*3) */
    servicio_carrera.showReport();


}