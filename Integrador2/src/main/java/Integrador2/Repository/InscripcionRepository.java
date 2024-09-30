package Integrador2.Repository;


import Integrador2.DTO.ReporteDTO;
import Integrador2.Entities.Carrera;
import Integrador2.Entities.Estudiante;
import Integrador2.Entities.Inscripcion;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscripcionRepository extends BaseJPARepository<Inscripcion, Long> implements IInscripcionRepository {
    private static InscripcionRepository instance = null;

    public InscripcionRepository(EntityManager em) {
        super(em, Inscripcion.class, Long.class);
    }

    public static InscripcionRepository getInstance(EntityManager em) {
        if (instance == null) {
            instance = new InscripcionRepository(em);
        }
        return instance;
    }

    @Override
    public void matricularEstudiante(int dniEstudiante, String nombreCarrera, LocalDate fechaInscripcion) {

        String q = "SELECT e FROM Estudiante e WHERE e.documento = :dniEstudiante";
        List<Estudiante> e = em.createQuery(q, Estudiante.class).setParameter("dniEstudiante", dniEstudiante).getResultList();


        if (!e.isEmpty()) {
            String q2 = "SELECT c  FROM Carrera c WHERE c.nombreCarrera = :nombreCarrera";
            List<Carrera> c = em.createQuery(q2, Carrera.class).setParameter("nombreCarrera", nombreCarrera).getResultList();
            if (!c.isEmpty())
                persist(new Inscripcion(c.get(0), e.get(0), fechaInscripcion));
        }
    }

    @Override
    public List<ReporteDTO> getReporte() {
        List<ReporteDTO> res = new ArrayList<>();
        String queryCantInscriptos = "SELECT  i.carrera, c.nombreCarrera,  YEAR(i.fechaInscripcion), COUNT(i.estudiante) AS cantInscriptos" +
                ", SUM(CASE WHEN i.graduado = true THEN 1 ELSE 0 END) AS cantGraduados " +
                "FROM Inscripcion i " +
                "JOIN i.carrera c " +
                "GROUP BY  i.carrera, YEAR(i.fechaInscripcion)" +
                "ORDER BY YEAR(i.fechaInscripcion), c.nombreCarrera DESC ";
        List<Object[]> inscriptos = em.createQuery(queryCantInscriptos).getResultList();

        for (Object[] o : inscriptos) {
            Carrera carrera = (Carrera) o[0];
            Long idCarrera = carrera.getIdCarrera();
            String nombreCarrera = (String) o[1];
            int anio = (int) o[2];
            Long cantInscriptos = (Long) o[3];
            Long cantGraduados = (Long) o[4];

            ReporteDTO r = new ReporteDTO(idCarrera, nombreCarrera, anio, cantInscriptos.intValue(), cantGraduados.intValue());
            res.add(r);
        }

        return res;
    }
}
