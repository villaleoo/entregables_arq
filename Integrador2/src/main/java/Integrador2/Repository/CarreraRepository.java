package Integrador2.Repository;

import Integrador2.DTO.ReporteDTO;
import Integrador2.Entities.Carrera;
import Integrador2.Entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CarreraRepository extends BaseJPARepository<Carrera, Long> implements ICarreraRepository {
    private static CarreraRepository instance = null;

    public CarreraRepository(EntityManager em) {
        super(em, Carrera.class, Long.class);
    }

    public static CarreraRepository getInstance(EntityManager em) {
        if (instance == null) {
            instance = new CarreraRepository(em);
        }
        return instance;
    }

    @Override
    public List<Carrera> getCarrerasConEstudiantesInscriptos() {
        String q = "SELECT new Integrador2.DTO.CarreraDTO (c.nombreCarrera, COUNT(i.carrera) ) " +
                "FROM Inscripcion i " +
                "JOIN i.carrera c " +
                "GROUP BY (i.carrera) " +
                "ORDER BY COUNT(i.carrera) DESC";
        return em.createQuery(q).getResultList();
    }


}
