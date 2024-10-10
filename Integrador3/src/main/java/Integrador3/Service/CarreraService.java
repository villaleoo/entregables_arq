package Integrador3.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Integrador3.DTO.CarreraDTO;
import Integrador3.DTO.CarreraInscriptosDTO;
import Integrador3.Entities.Carrera;
import Integrador3.Repository.ICarreraRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

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

    public List<CarreraInscriptosDTO> getCarrerasConEstudiantesInscriptos() {
        return carreraRepository.getCarrerasConEstudiantesInscriptos();
    }
}
