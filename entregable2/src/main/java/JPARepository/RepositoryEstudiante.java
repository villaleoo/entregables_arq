package JPARepository;

import model.Carrera;
import model.Estudiante;
import utils.enums.Genero;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepositoryEstudiante extends BaseJPARepository<Estudiante,Integer>{
    private static RepositoryEstudiante singleton;

    private RepositoryEstudiante(EntityManager em){
        super(Estudiante.class, Integer.class, em);
    }

    public static RepositoryEstudiante getInstance(EntityManager em){
        if(singleton == null){
            singleton = new RepositoryEstudiante(em);
        }
        return singleton;
    }


    public Estudiante findByNumLibreta(String num){
        String jpqlQuery= "SELECT e FROM Estudiante e WHERE e.num_libreta = :num";
        TypedQuery<Estudiante> q= em.createQuery(jpqlQuery, Estudiante.class);
        q.setParameter("num",num);

        Estudiante result;
        try {
            result = q.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }

        return result;
    }

    public List<Estudiante> findAllByGenero(Genero g){
        String jpqlQuery ="SELECT e FROM Estudiante e WHERE e.genero = :g";
        TypedQuery<Estudiante> q= em.createQuery(jpqlQuery, Estudiante.class);
        q.setParameter("g",g);

        List<Estudiante> results;
        try {
            results = q.getResultList();
        } catch (NoResultException e) {
            results = null;
        }

        return results;

    }

    public List<Estudiante> findAllByCiudadAndCarrera (String ciudad, Integer idCarrera){
        String jpqlQuery= "SELECT e FROM Estudiante e JOIN e.carreras_inscriptas i " +
                "WHERE e.ciudad_residencia = :ciudad AND i.carrera.id_carrera = :idCarrera";

        TypedQuery<Estudiante> q = em.createQuery(jpqlQuery, Estudiante.class);
        q.setParameter("ciudad",ciudad);
        q.setParameter("idCarrera",idCarrera);

        List<Estudiante> results;
        try {
            results = q.getResultList();
        } catch (NoResultException e) {
            results = null;
        }

        return results;

    }



}
