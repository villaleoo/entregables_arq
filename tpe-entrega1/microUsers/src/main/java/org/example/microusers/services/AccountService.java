package org.example.microusers.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microusers.DTO.clientAccount.ClientAccountDTO;
import org.example.microusers.DTO.clientAccount.RechargeDTO;
import org.example.microusers.DTO.clientAccount.UpdateAccountDTO;
import org.example.microusers.DTO.payments.DebitDetailDTO;
import org.example.microusers.model.Cuenta;
import org.example.microusers.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;


    public List<Cuenta> findAll() {
        return this.repository.findAll();
    }


    public ClientAccountDTO findById(Long id){
        return this.repository.findByIdProtected(id);
    }

    public ClientAccountDTO save(ClientAccountDTO entity){
        Cuenta newAccount = new Cuenta(entity.getDischargeDate(),entity.getBalance(),entity.getId_mercadoPago(),
                entity.getNameAccount(),entity.isAvailable());
        this.repository.save(newAccount);

        return entity;
    }

    public ClientAccountDTO disable(Long id){
        Optional<Cuenta> res = this.repository.findById(id);

        if(res.isPresent()){
            Cuenta c = res.get();
            c.setAvailable(false);
            this.repository.save(c);

            return new ClientAccountDTO(c.getNameAccount(),c.getDischargeDate(),c.getBalance(),c.getId_mercadoPago(),c.isAvailable());
        }

        throw new EntityNotFoundException("No se pudo encontrar cuenta con id: "+id);
    }

    public UpdateAccountDTO update(Long id, UpdateAccountDTO entity){
        Optional<Cuenta> c = this.repository.findById(id);

        if(c.isPresent()){
            Cuenta update= c.get();
            update.setBalance(entity.getBalance());
            update.setNameAccount(entity.getNameAccount());
            update.setId_mercadoPago(entity.getId_mercadoPago());
            update.setAvailable(entity.isAvailable());

            this.repository.save(update);

            return entity;
        }

        throw new EntityNotFoundException("No se encontro cuenta con id: "+id);
    }


    public ClientAccountDTO delete(Long id){
        Optional<Cuenta> c = this.repository.findById(id);
        if(c.isPresent()){
            Cuenta delete=c.get();
            this.repository.delete(delete);

            return new ClientAccountDTO(delete.getNameAccount(), delete.getDischargeDate(),delete.getBalance(), delete.getId_mercadoPago(), delete.isAvailable());
        }

        throw new EntityNotFoundException("No se encontro cuenta con id: "+id);
    }

    public UpdateAccountDTO recharge(Long id, RechargeDTO rec){
        Optional<Cuenta> res =this.repository.findById(id);
        System.out.println(this.repository.findById(id));

        if(res.isPresent()){
            Cuenta c = res.get();
            c.setBalance(c.getBalance()+rec.getAmount());
            this.repository.save(c);

            return new UpdateAccountDTO(c.getNameAccount(),c.getBalance(),c.getId_mercadoPago(),c.isAvailable());
        }

        throw new EntityNotFoundException("No se encontro cuenta con id:"+id);
    }



    public DebitDetailDTO debitCredit(Long id, DebitDetailDTO total){
        Optional<Cuenta> res = this.repository.findById(id);
        System.out.println(total);
        if(res.isPresent()){
            Cuenta c = res.get();
            int newTotal=(int) Math.round(c.getBalance()-total.getTotalDebit());
            if(newTotal < 0){
                int desc=Math.abs((int) Math.round(c.getBalance()-total.getTotalDebit()));
                c.setBalance(0);
                this.repository.save(c);
                throw new EntityNotFoundException("No se pudo cobrar el total por falta de saldo. Se descontaron: "+desc);
            }
            c.setBalance(newTotal);
            this.repository.save(c);

            return total;
        }

        throw new EntityNotFoundException("No se encontro cuenta con id "+id+" y no se pudo completar el debito.");
    }

}
