package Service;

import DTO.InformeCarrera;
import Entities.Carrera;
import JPARepository.RepositoryCarrera;

import java.util.List;

public class ServiceCarrera {
    private RepositoryCarrera repo;

    public ServiceCarrera(RepositoryCarrera repo) {
        this.repo = repo;
    }

    public List<Carrera> getAllOrderEnrolled() {
        return this.repo.findAllByEnrolled();
    }

    public Carrera insert(Carrera c) {
        return this.repo.persist(c);
    }

    public void showReport() {
        List<InformeCarrera> i = this.repo.getReport();
        System.out.println("\\t INFORME DE CARRERAS \\n");
        for (InformeCarrera inf : i) {
            System.out.println(inf);
        }
    }
}
