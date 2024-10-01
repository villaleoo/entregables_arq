package JPARepository;

import DTO.Egreso;
import DTO.InformeCarrera;
import DTO.Inscripcion;
import Entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class RepositoryCarrera extends BaseJPARepository<Carrera, Integer> {
    private static RepositoryCarrera sgn;

    private RepositoryCarrera(EntityManager em) {
        super(Carrera.class, Integer.class, em);
    }

    public static RepositoryCarrera getInstance(EntityManager em) {
        if (sgn == null) {
            sgn = new RepositoryCarrera(em);
        }
        return sgn;
    }

    public List<Carrera> findAllByEnrolled() {
        String jpqlQuery = "SELECT c FROM Carrera c JOIN c.inscripciones i GROUP BY c ORDER BY COUNT(i) DESC";
        TypedQuery<Carrera> q = em.createQuery(jpqlQuery, Carrera.class);
        List<Carrera> results;
        try {
            results = q.getResultList();
        } catch (Exception e) {
            results = null;
        }
        return results;
    }

    public List<InformeCarrera> getReport() {
        String carrerasQuery = "SELECT c FROM Carrera c ORDER BY c.nombre_carrera ASC";
        List<Carrera> carreras = em.createQuery(carrerasQuery, Carrera.class).getResultList();

        List<InformeCarrera> report = new ArrayList<>();

        for (Carrera carrera : carreras) {
            List<Inscripcion> inscripciones = this.findInscripciones(carrera);
            List<Egreso> egresos = this.findEgresos(carrera);

            report.add(new InformeCarrera(carrera.getNombre_carrera(), inscripciones, egresos));
        }
        return report;
    }

    private List<Inscripcion> findInscripciones(Carrera carrera) {

        String inscripcionesQuery = "SELECT new DTO.Inscripcion(i.id_inscripcion.estudianteId, i.fecha_inscripcion, i.fecha_egreso) " +
                "FROM Inscripcion i WHERE i.carrera = :carrera " +
                "ORDER BY i.fecha_inscripcion DESC";

        List<Inscripcion> inscripciones = em.createQuery(inscripcionesQuery, Inscripcion.class).setParameter("carrera", carrera).getResultList();

        return inscripciones;
    }

    private List<Egreso> findEgresos(Carrera carrera) {

        String egresosQuery = "SELECT new DTO.Egreso(i.id_inscripcion.estudianteId, i.fecha_egreso) " +
                "FROM Inscripcion i WHERE i.carrera = :carrera AND i.fecha_egreso IS NOT NULL " +
                "ORDER BY i.fecha_egreso DESC";

        List<Egreso> egresos = em.createQuery(egresosQuery, Egreso.class).setParameter("carrera", carrera).getResultList();

        return egresos;
    }
}
