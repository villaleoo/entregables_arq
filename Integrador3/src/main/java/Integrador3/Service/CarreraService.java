package Integrador3.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public void addCarrera(CarreraDTO carrera) {
        Carrera newCarrera = new Carrera();
        newCarrera.setNombreCarrera(carrera.getNombreCarrera());
        carreraRepository.save(newCarrera);
    }

    public CarreraDTO getCarreraById(Long id) {
        Carrera carrera = carreraRepository.findById(id).orElse(null);
        if (carrera != null)
            return new CarreraDTO(carrera.getNombreCarrera());
        return null;
    }

    public List<CarreraDTO> getAllCarreras() {
        List<Carrera> carreras = carreraRepository.findAll();
        List<CarreraDTO> res = new LinkedList<>();

        for (Carrera c : carreras)
            res.add(new CarreraDTO(c.getNombreCarrera()));

        return res;
    }


    public void deleteCarrera(Long id) {
        if (!carreraRepository.existsById(id))
            throw new EntityNotFoundException("Carrera no encontrada con id: " + id);
        carreraRepository.deleteById(id);
    }

    public CarreraDTO updateCarrera(Long id, CarreraDTO carrera) {
        if(!carreraRepository.existsById(id))
            throw new EntityNotFoundException("Carrera no encontrada con id: " + id);
        Carrera newCarrera = carreraRepository.findById(id).orElse(null);
        newCarrera.setNombreCarrera(carrera.getNombreCarrera());
        carreraRepository.save(newCarrera);
        return carrera;
    }

    public List<CarreraInscriptosDTO> getCarrerasConEstudiantesInscriptos() {
        return carreraRepository.getCarrerasConEstudiantesInscriptos();
    }
}
