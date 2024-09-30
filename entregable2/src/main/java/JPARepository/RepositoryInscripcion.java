package JPARepository;

import model.Estudiante;
import model.Inscripcion;
import model.InscripcionId;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class RepositoryInscripcion extends BaseJPARepository<Inscripcion,InscripcionId> {
    private static RepositoryInscripcion singleton;

    private RepositoryInscripcion(EntityManager em) {
        super(Inscripcion.class, InscripcionId.class, em);
    }

    public static RepositoryInscripcion getInstance (EntityManager em){
        if(singleton == null){
            singleton=new RepositoryInscripcion(em);
        }

        return singleton;
    }



}
