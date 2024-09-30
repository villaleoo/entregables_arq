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
import utils.enums.Facultad;
import utils.enums.Genero;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public static void main (String [] args){

    EntityManagerFactory emf= Persistence.createEntityManagerFactory("sistemaUniversidad");
    EntityManager em = emf.createEntityManager();

    RepositoryCarrera rc =RepositoryCarrera.getInstance(em);
    RepositoryEstudiante re = RepositoryEstudiante.getInstance(em);
    RepositoryInscripcion ri = RepositoryInscripcion.getInstance(em);



    ServiceCarrera servicio_carrera = new ServiceCarrera(rc);
    ServiceEstudiante servicio_estudiante = new ServiceEstudiante(re);
    ServiceInscripcion servicio_inscripcion = new ServiceInscripcion(ri,re,rc);


    Estudiante e1 = new Estudiante("Pedro","Perez",LocalDate.of(1994,8,3),Genero.MASCULINO,39564322,"tandil","678rt");

    Estudiante e2 = new Estudiante("Julian","Sanchez",LocalDate.of(1998,3,2),Genero.MASCULINO,56778993,"tandil","663if");

    Estudiante e3 = new Estudiante("Federico", "Gomez",LocalDate.of(2000,3,9),Genero.OTRO,51079564,"tandil","690jy");

    Estudiante e4 = new Estudiante("Gabriela", "figueroa", LocalDate.of(1997,12,10),Genero.FEMENINO,404040404,"tandil","388kj");

    Estudiante e5 = new Estudiante("Juliana","ortiz", LocalDate.of(1993,3,12),Genero.FEMENINO,45067894,"tandil","889tt");

    Estudiante e6= new Estudiante("Agustina","hernandez",LocalDate.of(1998,7,7),Genero.FEMENINO,41210677,"tandil","776hyg");

    Carrera c1 = new Carrera("Tecnicatura universitaria en desarrollo de aplicaciones informaticas",Facultad.EXACTAS);
    Carrera c2 = new Carrera("Ingenieria en sistemas",Facultad.EXACTAS);
    Carrera c3 = new Carrera("Licenciatura en administracion de empresas",Facultad.ECONOMICAS);
    Carrera c4 = new Carrera("Medicina veterinaria",Facultad.VETERINARIAS);

    /*
    * En carrera 1: 3 estudiantes
    * En carrera 2: 2 estudiantes
    * En carrera 3: 1 estudiante
    * En carrera 4: 2 estudiantes
    *
    * fechas de inscripcion y de egreso configuradas/personalizadas (por defecto la inscripcion es la fecha del dia Localdate.now() y la de egreso=null)
    * */


    ///////////////////////////////////////PERSISTENCIA/////////////////////////////////////
    /*
    servicio_carrera.insert(c1);
    servicio_carrera.insert(c2);
    servicio_carrera.insert(c3);
    servicio_carrera.insert(c4);

    servicio_estudiante.insert(e1);
    servicio_estudiante.insert(e2);
    servicio_estudiante.insert(e3);
    servicio_estudiante.insert(e4);
    servicio_estudiante.insert(e5);
    servicio_estudiante.insert(e6);

   servicio_inscripcion.insert(5,1);
   servicio_inscripcion.insert(5,2);
   servicio_inscripcion.insert(6,1);
   servicio_inscripcion.insert(7,2);
   servicio_inscripcion.insert(7,1);
   servicio_inscripcion.insert(8,3);
   servicio_inscripcion.insert(9,4);
   servicio_inscripcion.insert(10,4);

   servicio_inscripcion.updateInscripcion(5,1,LocalDate.of(2020,3,3));
   servicio_inscripcion.updateEgreso(5,1,LocalDate.of(2024,6,12));
   servicio_inscripcion.updateInscripcion(5,2,LocalDate.of(2020,3,3));

   servicio_inscripcion.updateInscripcion(6,1,LocalDate.of(2021,3,3));
   servicio_inscripcion.updateEgreso(6,1,LocalDate.of(2023,12,12));

   servicio_inscripcion.updateInscripcion(7,2,LocalDate.of(2017,3,3));
   servicio_inscripcion.updateEgreso(7,2,LocalDate.of(2022,12,12));

   servicio_inscripcion.updateInscripcion(8,3,LocalDate.of(2019,3,3));
   servicio_inscripcion.updateEgreso(8,3,LocalDate.of(2024,3,3));

   servicio_inscripcion.updateInscripcion(9,4,LocalDate.of(2018,3,3));
   servicio_inscripcion.updateEgreso(9,4,LocalDate.of(2023,12,12));

   servicio_inscripcion.updateInscripcion(10,4,LocalDate.of(2015,3,3));
*/

    //////////////////////////////////////////////SERVICIOS/////////////////////////////////////////////

    /*A) SE DIO DE ALTA ESTUDIANTES EN LA PERSISTENCIA servicio_estudiante.insert()*/
    /*B) SE MATRICULA UN ESTUDIANTE EN LA PERSISTENCIA servicio_inscripcion.insert()*/

    /*C) */
    List<Estudiante> estudiantesOrdenadosAlfabeticamente = servicio_estudiante.getAllAlphabetically();
    System.out.println("\t TOTALIDAD DE ESTUDIANTES ORDENADOS POR APELLIDO ALFABETICAMENTE: \n");
    for(Estudiante eAlf : estudiantesOrdenadosAlfabeticamente){
        System.out.println(eAlf);
    }

    /*D) */
    String num_libreta="388kj";

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
    Integer id_carrera= 1;
    List<Estudiante> estudiantes_por_carrera_ciudad= servicio_estudiante.getAllByCiudadANDCarrera("tandil",id_carrera);
    System.out.println("\n \tESTUDIANTES QUE RESIDEN EN TANDIL Y ESTUDIAN LA CARRERA CON ID="+id_carrera+"" +"\n");

    for(Estudiante est_ciudad : estudiantes_por_carrera_ciudad){
        System.out.println(est_ciudad);
    }

    /*3) */
    servicio_carrera.showReport();



}