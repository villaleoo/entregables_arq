package service;


import JPARepository.RepositoryCarrera;
import JPARepository.RepositoryEstudiante;
import JPARepository.RepositoryInscripcion;

import model.Carrera;
import model.Estudiante;
import model.Inscripcion;
import model.InscripcionId;

import java.time.LocalDate;
import java.util.List;


public class ServiceInscripcion {

    private RepositoryInscripcion repository;
    private RepositoryEstudiante repositoryEstudiante;
    private RepositoryCarrera repositoryCarrera;


    public ServiceInscripcion(RepositoryInscripcion repository, RepositoryEstudiante repEstudiante, RepositoryCarrera repCarrera){
        this.repository=repository;
        this.repositoryEstudiante=repEstudiante;
        this.repositoryCarrera=repCarrera;
    }


    public Inscripcion insert (Inscripcion i){
        Estudiante e = this.repositoryEstudiante.findById(i.getIdEstudiante());
        Carrera c = this.repositoryCarrera.findById(i.getIdCarrera());

        if (e == null || c == null) return null;
        return this.repository.persist(i);

    }

    public List<Inscripcion> getAll(){
        return this.repository.findAll();
    }

    public Inscripcion findById(Integer id_estudiante, Integer id_carrera){
        return  this.repository.findById(new InscripcionId(id_estudiante,id_carrera));
    }





}
