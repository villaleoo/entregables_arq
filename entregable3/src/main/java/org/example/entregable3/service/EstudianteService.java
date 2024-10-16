package org.example.entregable3.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.entregable3.DTO.EstudianteDTO;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.repository.EstudianteRepository;
import org.example.entregable3.utils.enums.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements BaseService<EstudianteDTO,Integer>{

    @Autowired
    private EstudianteRepository repository;


    public List<EstudianteDTO> findAllByCiudadAndCarrera(String ciudad, Integer id_carrera) throws Exception {
        List<Estudiante> r = this.repository.findAllByCiudadAndCarrera(ciudad,id_carrera);
        List<EstudianteDTO> result = new ArrayList<>();

        for(Estudiante e : r){
            result.add(new EstudianteDTO(e.getDni(),e.getNombre(),e.getApellido(),
                    e.getFecha_nacimiento(),e.getGenero(),e.getCiudad_residencia(),e.getNum_libreta()));
        }
        return result;
    }

    public List<EstudianteDTO> findAllByGenero(Genero g) {
        List<Estudiante> r = this.repository.findAllByGenero(g);
        List<EstudianteDTO> result = new ArrayList<>();

        for(Estudiante e : r){
            result.add(new EstudianteDTO(e.getDni(),e.getNombre(),e.getApellido(),
                    e.getFecha_nacimiento(),e.getGenero(),e.getCiudad_residencia(),e.getNum_libreta()));
        }
        return result;
    }

    public List<EstudianteDTO> findAllAlphabeticSurname() {
        List<Estudiante> r = this.repository.findAllBySortAlpabheticSurname();
        List<EstudianteDTO> result = new ArrayList<>();

        for(Estudiante e : r){
            result.add(new EstudianteDTO(e.getDni(),e.getNombre(),e.getApellido(),
                    e.getFecha_nacimiento(),e.getGenero(),e.getCiudad_residencia(),e.getNum_libreta()));
        }
        return result;
    }

    public EstudianteDTO findByEnrolled(String num){
        Estudiante e = this.repository.findByNumeroLibreta(num);
        if(e!= null){
            return  new EstudianteDTO(e.getDni(),e.getNombre(),e.getApellido(),
                    e.getFecha_nacimiento(),e.getGenero(),e.getCiudad_residencia(),e.getNum_libreta());
        }
        return null;
    }


    @Transactional
    @Override
    public List<EstudianteDTO> findAll() {
        List<Estudiante> r= this.repository.findAll();
        List<EstudianteDTO> results=new ArrayList<>();

        for(Estudiante e : r){
            results.add(new EstudianteDTO(e.getDni(),e.getNombre(),e.getApellido(),
                    e.getFecha_nacimiento(),e.getGenero(),e.getCiudad_residencia(),e.getNum_libreta()));

        }

        return results;
    }

    @Transactional
    @Override
    public EstudianteDTO findById(Integer id){
        Optional<Estudiante> result = this.repository.findById(id);
        Estudiante e = result.get();
        if(result.isPresent()){
            return new EstudianteDTO(e.getDni(),e.getNombre(),e.getApellido(),e.getFecha_nacimiento(),
                    e.getGenero(),e.getCiudad_residencia(),e.getNum_libreta());
        }
        return null;
    }

    @Transactional
    @Override
    public EstudianteDTO save(EstudianteDTO entity){
        Estudiante e = new Estudiante(entity.getNombre(),entity.getApellido(),
                entity.getFecha_nacimiento(),entity.getGenero(), entity.getDocumento(), entity.getCiudad(), entity.getNroLibreta());
        this.repository.save(e);
        return entity;
    }

    @Transactional
    @Override
    public EstudianteDTO update(Integer id, EstudianteDTO entity){
        Optional<Estudiante> result = repository.findById(id);
        if(result != null){
            Estudiante e = result.get();
            e.setDni(entity.getDocumento());
            e.setNum_libreta(entity.getNroLibreta());
            e.setApellido(entity.getApellido());
            e.setNombre(entity.getNombre());
            e.setGenero(entity.getGenero());
            e.setCiudad_residencia(entity.getCiudad());
            e.setFecha_nacimiento(entity.getFecha_nacimiento());

            this.repository.save(e);
            return entity;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean delete(Integer id){
        if (!this.repository.existsById(id)) {
            throw new EntityNotFoundException("Estudiante no encontrado con id: " + id);
        }
        this.repository.deleteById(id);
        return true;
    }
}
