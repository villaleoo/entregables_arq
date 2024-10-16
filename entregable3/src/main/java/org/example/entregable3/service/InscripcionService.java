package org.example.entregable3.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.entregable3.DTO.CarreraDTO;
import org.example.entregable3.DTO.EgresoDTO;
import org.example.entregable3.DTO.InscripcionDTO;
import org.example.entregable3.DTO.ReporteCarreraDTO;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.model.Inscripcion;
import org.example.entregable3.model.InscripcionId;
import org.example.entregable3.repository.CarreraRepository;
import org.example.entregable3.repository.EstudianteRepository;
import org.example.entregable3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService implements BaseService<InscripcionDTO,InscripcionId> {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    @Override
    public List<InscripcionDTO> findAll(){
        List<Inscripcion>  r = this.inscripcionRepository.findAll();
        List<InscripcionDTO> results = new ArrayList<>();

        for(Inscripcion i : r){
            results.add(new InscripcionDTO(i.getEstudiante().getDni(), i.getCarrera().getId_carrera(), i.getFecha_inscripcion(),i.getFecha_egreso()));

        }
        return results;
    }

    @Transactional
    @Override
    public InscripcionDTO findById(InscripcionId id){
        Optional<Inscripcion> r = this.inscripcionRepository.findById(id);
        Inscripcion i = r.get();
        if(i != null){
            return  new InscripcionDTO(i.getEstudiante().getId(), i.getCarrera().getId_carrera(),
                    i.getFecha_inscripcion(),i.getFecha_egreso());
        }

        return null;

    }


    @Override
    public InscripcionDTO save(InscripcionDTO entity){
        Estudiante e = this.estudianteRepository.findByDni(entity.getDniEstudiante());
        Carrera c = this.carreraRepository.findById(entity.getIdCarrera()).get();
        InscripcionId id = new InscripcionId(e.getId(),entity.getIdCarrera());


        Inscripcion i = new Inscripcion(e,c);
        i.setId_inscripcion(id);

        this.inscripcionRepository.save(i);
        return entity;
    }

    @Override
    public InscripcionDTO update(InscripcionId id, InscripcionDTO entity){
        Inscripcion i = this.inscripcionRepository.findById(id).get();
        Carrera c = this.carreraRepository.findById(entity.getIdCarrera()).get();
        Estudiante e = this.estudianteRepository.findByDni(entity.getDniEstudiante());

        if(i!=null){
            i.setFecha_inscripcion(entity.getFechaInscripcion());
            i.setFecha_egreso(entity.getFechaEgreso());
            i.setCarrera(c);
            i.setEstudiante(e);
            this.inscripcionRepository.save(i);
        }
        return entity;
    }

    @Override
    public boolean delete(InscripcionId id) throws Exception {
        if (!this.inscripcionRepository.existsById(id)){
            throw new EntityNotFoundException("Inscripcion no encontrada.");
        }

        this.inscripcionRepository.deleteById(id);
        return true;
    }


    public List<ReporteCarreraDTO> getReport(){
        List<Carrera> allByTitle = this.carreraRepository.findAllByTitleAsc();
        List<ReporteCarreraDTO> report = new ArrayList<>();

        for(Carrera c : allByTitle){

            report.add(new ReporteCarreraDTO(c.getTitulo(),this.carreraRepository.findInscripciones(c),this.carreraRepository.findEgresos(c)));
        }

        return report;
    }
}
