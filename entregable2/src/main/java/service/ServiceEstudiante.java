package service;

import JPARepository.RepositoryEstudiante;
import model.Estudiante;
import utils.enums.Genero;

import java.util.Comparator;
import java.util.List;


public class ServiceEstudiante {

    private RepositoryEstudiante repository;

    public ServiceEstudiante(RepositoryEstudiante repository){
        this.repository=repository;
    }

    public List<Estudiante> getAllAlphabetically(){
        List<Estudiante> results = this.repository.findAll();
        results.sort(Comparator.comparing(Estudiante::getApellido));

        return results;
    }

    public Estudiante getByNumLibreta(String num){
        return this.repository.findByNumLibreta(num);
    }

    public List<Estudiante> getAllByGenero(Genero g){
        return this.repository.findAllByGenero(g);
    }

    public List<Estudiante> getAllByCiudadANDCarrera(String ciudad, Integer idCarrera){
        return this.repository.findAllByCiudadAndCarrera(ciudad,idCarrera);
    }

    public Estudiante insert (Estudiante e){
        return this.repository.persist(e);
    }

}
