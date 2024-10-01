package JPARepository;

import DTO.EgresoDTO;
import DTO.InscripcionDTO;
import DTO.ReporteCarreraDTO;
import model.Carrera;
import model.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositoryCarrera extends BaseJPARepository<Carrera,Integer> {
    private static RepositoryCarrera singleton;

    private RepositoryCarrera(EntityManager em) {
        super(Carrera.class, Integer.class, em);
    }

    public static RepositoryCarrera getInstance(EntityManager em){
        if(singleton == null){
            singleton = new RepositoryCarrera(em);
        }

        return singleton;
    }

    public List<Carrera> findAllByEnrolled(){
        String jpqlQuery= "SELECT c FROM Carrera c JOIN c.inscripciones i GROUP BY c ORDER BY COUNT(i) DESC";
        TypedQuery<Carrera> q = em.createQuery(jpqlQuery, Carrera.class);

        List<Carrera> results;
        try {
            results = q.getResultList();
        } catch (NoResultException e) {
            results = null;
        }

        return results;

    }

    /*Esta funcion obtiene cada carrera ordenada por nombre, por cada carrera obtenida busca sus inscriptos y sus egresados y lo agrega al reporte */
    public List<ReporteCarreraDTO> getReport(){
        String carrerasQuery = "SELECT c FROM Carrera c ORDER BY c.titulo ASC";
        List<Carrera> carreras = em.createQuery(carrerasQuery, Carrera.class).getResultList();

        List<ReporteCarreraDTO> report = new ArrayList<>();

        for (Carrera carrera : carreras) {
            List<InscripcionDTO> inscripciones = this.findInscripciones(carrera);
            List<EgresoDTO> egresos = this.findEgresos(carrera);

            report.add(new ReporteCarreraDTO(carrera.getTitulo(), inscripciones, egresos));
        }

        return report;
    }

    private List<InscripcionDTO> findInscripciones(Carrera carrera){

        String inscripcionesQuery = "SELECT new DTO.InscripcionDTO(i.id_inscripcion.estudianteId, i.fecha_inscripcion, i.fecha_egreso) " +
                "FROM Inscripcion i WHERE i.carrera = :carrera " +
                "ORDER BY i.fecha_inscripcion DESC";

        List<InscripcionDTO> inscripciones = em.createQuery(inscripcionesQuery, InscripcionDTO.class).setParameter("carrera", carrera).getResultList();

        return inscripciones;
    }

    private List<EgresoDTO> findEgresos(Carrera carrera){

        String egresosQuery = "SELECT new DTO.EgresoDTO(i.id_inscripcion.estudianteId, i.fecha_egreso) " +
                "FROM Inscripcion i WHERE i.carrera = :carrera AND i.fecha_egreso IS NOT NULL " +
                "ORDER BY i.fecha_egreso DESC";

        List<EgresoDTO> egresos = em.createQuery(egresosQuery, EgresoDTO.class).setParameter("carrera", carrera).getResultList();

        return egresos;
    }




}
