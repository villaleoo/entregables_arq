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
    private RepositoryEstudiante repEstudiante;
    private RepositoryCarrera repositoryCarrera;


    public ServiceInscripcion(RepositoryInscripcion repository, RepositoryEstudiante repEstudiante, RepositoryCarrera repCarrera){
        this.repository=repository;
        this.repEstudiante=repEstudiante;
        this.repositoryCarrera=repCarrera;
    }

    public Inscripcion insert (Integer id_persona, Integer id_carrera){
        Estudiante e = this.repEstudiante.findById(id_persona);
        Carrera c = this.repositoryCarrera.findById(id_carrera);
        if (e == null || c == null) return null;
        return this.repository.persist(new Inscripcion(e, c));
    }

    public List<Inscripcion> getAll(){
        return this.repository.findAll();
    }

    public Inscripcion findById(Integer id_estudiante, Integer id_carrera){
        return  this.repository.findById(new InscripcionId(id_estudiante,id_carrera));
    }

    public Inscripcion updateEgreso (Integer id_estudiante, Integer id_carrera, LocalDate fecha_egreso){
        Inscripcion i = this.repository.findById(new InscripcionId(id_estudiante,id_carrera));
        i.setFechaEgreso(fecha_egreso);

        return this.repository.update(i);
    }

    public Inscripcion updateInscripcion(Integer id_estudiante,Integer id_carrera, LocalDate fecha_inscripcion){
        Inscripcion i = this.repository.findById(new InscripcionId(id_estudiante,id_carrera));
        i.setFecha_inscripcion(fecha_inscripcion);

        return this.repository.update(i);
    }



}
