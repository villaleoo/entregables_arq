package service;

import DTO.ReporteCarreraDTO;
import JPARepository.RepositoryCarrera;
import model.Carrera;

import java.time.LocalDate;
import java.util.List;

public class ServiceCarrera {
    private RepositoryCarrera repository;

    public ServiceCarrera(RepositoryCarrera repository){
        this.repository=repository;
    }

    public List<Carrera> getAllOrderEnrolled(){
        return this.repository.findAllByEnrolled();
    }

    public Carrera insert (Carrera c){
        return this.repository.persist(c);
    }

    public void showReport(){

        List<ReporteCarreraDTO> r = this.repository.getReport();

        System.out.println("\t REPORTE DE CARRERAS \n");
        for(ReporteCarreraDTO rep : r){
            System.out.println(rep);
        }

    }

    public Carrera getById(Integer id){
        return this.repository.findById(id);
    }
}
