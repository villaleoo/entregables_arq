package Service;

import Entities.Estudiante;
import JPARepository.RepositoryEstudiante;
import utils.enums.Genero;

import java.util.Comparator;
import java.util.List;

public class ServiceEstudiante {
    private RepositoryEstudiante repo;

    public ServiceEstudiante(RepositoryEstudiante repo) {
        this.repo = repo;
    }

    public List<Estudiante> getAllAlphabetically() {
        List<Estudiante> results = this.repo.findAll();
        results.sort(Comparator.comparing(Estudiante::getApellido));
        return results;
    }

    public List<Estudiante> getAllByGenero(Genero g) {
        return this.repo.findAllByGenero(g);
    }

    public List<Estudiante> getAllByCiudadAndCarrera(String ciudad, Integer idCarrera) {
        return this.repo.findAllByCiudadAndCarrera(ciudad, idCarrera);
    }

    public Estudiante getByNumLibreta(String n) {
        return this.repo.findByNumLibreta(n);
    }

    public Estudiante insert(Estudiante est) {
        return this.repo.persist(est);
    }

}
