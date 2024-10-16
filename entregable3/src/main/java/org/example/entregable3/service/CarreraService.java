package org.example.entregable3.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.entregable3.DTO.*;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarreraService  implements BaseService<CarreraDTO,Integer>{

    @Autowired
    private CarreraRepository repository;



    public List<CarreraInscriptosDTO> findAllByEnrolled(){
       return this.repository.getAllEnrolled();
    }

    @Override
    public List<CarreraDTO> findAll() throws Exception {
        List<Carrera>carreras= this.repository.findAll();
        List<CarreraDTO> results= new ArrayList<>();

        for(Carrera c : carreras){
            results.add(new CarreraDTO(c.getId_carrera(),c.getTitulo(), c.getFacultad()));
        }

        return results;
    }

    @Override
    public CarreraDTO findById(Integer id) throws Exception {
        Optional<Carrera> c = this.repository.findById(id);
        if(c != null){
            return new CarreraDTO(c.get().getId_carrera(), c.get().getTitulo(),c.get().getFacultad());
        }
        return null;
    }



    @Transactional
    @Override
    public CarreraDTO save(CarreraDTO c) throws Exception {
        Carrera c1 = new Carrera(c.getNombreCarrera(),c.getFacultad());

        this.repository.save(c1);
        return c;
    }

    @Transactional
    @Override
    public CarreraDTO update(Integer id, CarreraDTO c) throws Exception {
        if(!repository.existsById(id))
            throw new EntityNotFoundException("Carrera con id= "+ id+" no encontrada.");

        Carrera c1= this.repository.findById(id).orElse(null);
        c1.setTitulo(c.getNombreCarrera());
        this.repository.save(c1);

        return c;
    }

    @Transactional
    @Override
    public boolean delete(Integer id) throws Exception {
        if (!this.repository.existsById(id)){
            throw new EntityNotFoundException("Carrera no encontrada con id: " + id);

        }
        this.repository.deleteById(id);
        return true;

    }
}
