package org.example.microusers.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microusers.DTO.payments.*;
import org.example.microusers.model.MediosDePago;
import org.example.microusers.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentsService {


    @Autowired
    PaymentsRepository repository;


    public ResponseNewPaymentDTO create(NewPaymentDTO newPayment){
        Optional<MediosDePago> res = this.repository.findById(newPayment.getId());

        if(res.isEmpty()){
            MediosDePago mp = new MediosDePago();
            mp.setId(newPayment.getId());
            mp.setId_account(newPayment.getId_account());
            mp.setBalance(0);

            this.repository.save(mp);

            return new ResponseNewPaymentDTO("Medio de pago agregado con exito.",newPayment.getId());
        }

        throw new RuntimeException("El medio de pago ingresado ya pertenece a una cuenta.");
    }

    public MediosDePago findById(Long id){
        Optional<MediosDePago> res = this.repository.findByIdAccount(id);

        return res.orElse(null);

    }


    public ResponseRechargeDTO recharge(Long id, RechargeDTO recharge){
        Optional<MediosDePago> res =this.repository.findByIdAccount(id);

        if(res.isPresent()){
            MediosDePago mp = res.get();

            mp.setBalance(mp.getBalance() + recharge.getAmount());

            this.repository.save(mp);

            return new ResponseRechargeDTO(id,mp.getBalance());

        }

        throw new EntityNotFoundException("No se encontro cuenta con id:"+id);
    }



    public DebitDetailDTO debitCredit(Long id, DebitDetailDTO total){
        Optional<MediosDePago> res = this.repository.findByIdAccount(id);

        if(res.isEmpty()){
            throw new EntityNotFoundException("No se encontro medio de pago valido.");
        }


        MediosDePago mp = res.get();

        int newTotal=(int) Math.round(mp.getBalance()-total.getTotalDebit());

        if(newTotal < 0){
            int desc=Math.abs((int) Math.round(mp.getBalance()-total.getTotalDebit()));
            mp.setBalance(0);
            this.repository.save(mp);

            throw new EntityNotFoundException("No se pudo cobrar el total por falta de saldo. Se cobraron: $"+desc);
        }

        mp.setBalance(mp.getBalance()-total.getTotalDebit());
        this.repository.save(mp);

        return total;

    }


    public ResponseNewPaymentDTO delete(Long id_account){
        Optional<MediosDePago> res = this.repository.findByIdAccount(id_account);

        if(res.isEmpty()){
            throw new RuntimeException("No se encontro medio de pago asociado a cuenta "+id_account);
        }

        MediosDePago mp=res.get();

        this.repository.delete(mp);

        return new ResponseNewPaymentDTO("Medio de pago eliminado correctamente.",mp.getId());
    }

    public List<MediosDePago> findAll(){
        return this.repository.findAll();
    }

}
