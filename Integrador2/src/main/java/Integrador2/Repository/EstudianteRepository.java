package Integrador2.Repository;

import Integrador2.DTO.EstudianteCarreraDTO;
import Integrador2.Entities.Carrera;
import Integrador2.Entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EstudianteRepository extends BaseJPARepository<Estudiante, Long> implements IEstudianteRepository {
    private static EstudianteRepository instance = null;


    public EstudianteRepository(EntityManager em) {
        super(em, Estudiante.class, Long.class);
    }

    public static EstudianteRepository getInstance(EntityManager em) {
        if (instance == null) {
            instance = new EstudianteRepository(em);
        }
        return instance;
    }

    @Override
    public List<Estudiante> getEstudiantesOrderedBy(String criterio) {
        String aux = criterio.toLowerCase();
        if (!aux.equals("idestudiante") && !aux.equals("nombre") && !aux.equals("apellido") && !aux.equals("edad") &&
                !aux.equals("genero") && !aux.equals("documento") && !aux.equals("ciudad") && !aux.equals("nrolibreta"))
            return null;
        String q = "SELECT e FROM Estudiante e ORDER BY e." + criterio;
        return em.createQuery(q, Estudiante.class).getResultList();
    }

    @Override
    public Estudiante getEstudianteByNroLibreta(int nroLibreta) {
        String q = "SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta";
        List<Estudiante> e = em.createQuery(q, Estudiante.class).setParameter("nroLibreta", nroLibreta).getResultList();
        return e.isEmpty() ? null : e.get(0);
    }

    @Override
    public List<Estudiante> getEstudiantesByGenero(String genero) {
        String q = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        return em.createQuery(q, Estudiante.class).setParameter("genero", genero).getResultList();
    }

    @Override
    public List<EstudianteCarreraDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad) {
        String q = "SELECT new Integrador2.DTO.EstudianteCarreraDTO(e.idEstudiante, e.nombre, e.apellido, e.edad, e.genero, e.documento," +
                " e.ciudad, e.nroLibreta," +
                "c.idCarrera, c.nombreCarrera) FROM Estudiante e " +
                "JOIN e.carreras  i " +
                "JOIN i.carrera c " +
                "WHERE c.nombreCarrera = :carrera AND e.ciudad= :ciudad";

        TypedQuery<EstudianteCarreraDTO> query = em.createQuery(q, EstudianteCarreraDTO.class);
        query.setParameter("carrera", carrera);
        query.setParameter("ciudad", ciudad);
        return query.getResultList();
    }


}
