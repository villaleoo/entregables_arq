package Integrador3.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Integrador3.DTO.CarreraDTO;
import Integrador3.DTO.CarreraInscriptosDTO;
import Integrador3.Entities.Carrera;
import Integrador3.Repository.CarreraRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    public void add(CarreraDTO carrera) {
        Carrera newCarrera = new Carrera();
        newCarrera.setNombreCarrera(carrera.getNombreCarrera());
        carreraRepository.save(newCarrera);
    }

    public CarreraDTO getById(Long id) {
        Carrera carrera = carreraRepository.findById(id).orElse(null);
        if (carrera != null)
            return new CarreraDTO(carrera.getNombreCarrera());
        return null;
    }

    public Page<CarreraDTO> getAll(Pageable pageable) {
        Page<Carrera> carreras = carreraRepository.findAll(pageable);
        List<CarreraDTO> res = new LinkedList<>();

        for (Carrera c : carreras)
            res.add(new CarreraDTO(c.getNombreCarrera()));

        return new PageImpl<>(res, pageable, carreras.getTotalElements());
    }


    public void delete(Long id) {
        if (!carreraRepository.existsById(id))
            throw new EntityNotFoundException("Carrera no encontrada con id: " + id);
        carreraRepository.deleteById(id);
    }

    public CarreraDTO update(Long id, CarreraDTO carrera) {
        if (!carreraRepository.existsById(id))
            throw new EntityNotFoundException("Carrera no encontrada con id: " + id);
        Carrera newCarrera = carreraRepository.findById(id).orElse(null);
        newCarrera.setNombreCarrera(carrera.getNombreCarrera());
        carreraRepository.save(newCarrera);
        return carrera;
    }

    public Page<CarreraInscriptosDTO> getCarrerasConEstudiantesInscriptos(Pageable pageable) {
        return carreraRepository.getCarrerasConEstudiantesInscriptos(pageable);
    }
}
