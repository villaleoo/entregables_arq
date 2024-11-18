package org.example.microgestion.services;


import org.example.microgestion.DTO.fees.FeeDTO;
import org.example.microgestion.exceptions.FeeNotFoundException;
import org.example.microgestion.model.Tarifa;
import org.example.microgestion.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeService {

    @Autowired
    FeeRepository repository;

    public FeeDTO save(FeeDTO fee){
        this.repository.save(new Tarifa(fee.getNormalFee(),fee.getAdditionalFee(),fee.getDateValidity()));

        return  fee;
    }

    public List<Tarifa> findAll(){
        return this.repository.findAll();
    }

    public Tarifa delete(Long id){
        Optional<Tarifa> op = this.repository.findById(id);

        if(op.isPresent()){
            Tarifa delete=op.get();

            this.repository.delete(delete);
            return delete;
        }

        throw new FeeNotFoundException("No se encontro tarifa con id: "+id);
    }

    public FeeDTO update(Long id, FeeDTO fee){
        Optional<Tarifa> op = this.repository.findById(id);

        if(op.isPresent()){
            Tarifa update=op.get();
            update.setNormalFee(fee.getNormalFee());
            update.setAdditionalFee(fee.getAdditionalFee());
            update.setDateValidity(fee.getDateValidity());

            this.repository.save(update);
            return fee;
        }

        throw new FeeNotFoundException("No se encontro tarifa con id: "+id);
    }


}


