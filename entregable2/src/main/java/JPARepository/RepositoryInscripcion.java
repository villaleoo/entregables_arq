package JPARepository;

import Entities.Inscripcion;
import Entities.InscripcionId;

import javax.persistence.EntityManager;

public class RepositoryInscripcion extends BaseJPARepository<Inscripcion, InscripcionId> {
    private static RepositoryInscripcion sgn;

    private RepositoryInscripcion(EntityManager em) {
        super(Inscripcion.class, InscripcionId.class, em);
    }

    public static RepositoryInscripcion getInstance(EntityManager em) {
        if (sgn == null) {
            sgn = new RepositoryInscripcion(em);
        }
        return sgn;
    }
}
