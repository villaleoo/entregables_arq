package Service;

import Entities.Carrera;
import Entities.Estudiante;
import Entities.Inscripcion;
import Entities.InscripcionId;
import JPARepository.RepositoryCarrera;
import JPARepository.RepositoryEstudiante;
import JPARepository.RepositoryInscripcion;

import java.util.List;

public class ServiceInscripcion {
    private RepositoryInscripcion repo;
    private RepositoryCarrera repoCarrera;
    private RepositoryEstudiante repoEstudiante;

    public ServiceInscripcion(RepositoryInscripcion repo, RepositoryCarrera repoCarrera, RepositoryEstudiante repoEstudiante) {
        this.repo = repo;
        this.repoCarrera = repoCarrera;
        this.repoEstudiante = repoEstudiante;
    }

    public Inscripcion insert(Inscripcion i) {
        Estudiante e = this.repoEstudiante.findById(i.getIdEstudiante());
        Carrera c = this.repoCarrera.findById(i.getIdCarrera());

        if (e == null || c == null) return null;
        return this.repo.persist(i);
    }

    public List<Inscripcion> getAll() {
        return this.repo.findAll();
    }

    public Inscripcion findById(Integer id_estudiante, Integer id_carrera) {
        return this.repo.findById(new InscripcionId(id_estudiante, id_carrera));
    }
}
