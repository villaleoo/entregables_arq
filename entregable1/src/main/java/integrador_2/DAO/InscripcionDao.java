package integrador_2.DAO;

import integrador_2.entidades.Carrera;
import integrador_2.entidades.Estudiante;
import integrador_2.entidades.Inscripcion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class InscripcionDao {
    private String proyecto;
    public InscripcionDao(String pr) {
        this.proyecto = pr;
    }

    public void inscribirEstudiante(Integer id_estudiante, Integer id_carrera, Integer inicio, Integer graduacion) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Estudiante e = em.find(Estudiante.class, id_estudiante);
            Carrera c = em.find(Carrera.class, id_carrera);
            Inscripcion inscripcion = new Inscripcion(e, c, inicio, graduacion);
            em.persist(inscripcion);

            em.getTransaction().commit();
            em.close();
            System.out.println("Se inscribio el estudiante a la carrera exitosamente");
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
    }


    public void borrar(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Inscripcion i = em.find(Inscripcion.class, id);
            em.remove(i);

            em.getTransaction().commit();
            em.close();
            emf.close();
            System.out.println("Se ha borrado al estudiante de la carrera exitosamente");
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
    }


    public Object traer(int id) {
        return null;
    }


    public List traerTodos() {
        return List.of();
    }
}
