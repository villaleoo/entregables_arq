package org.example.microusers.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microusers.DTO.AccountDTO;
import org.example.microusers.DTO.ResponseDebitDTO;
import org.example.microusers.model.Cuenta;
import org.example.microusers.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;


    public List<Cuenta> findAll() {
        return this.repository.findAll();
    }


    public AccountDTO findById(Long id){
        return this.repository.findByIdProtected(id);
    }

    public AccountDTO save(AccountDTO entity){
        Cuenta newAccount = new Cuenta(entity.getDischargeDate(),entity.getBalance(),entity.getId_mercadoPago(),
                entity.getNameAccount(),entity.isAvailable());
        this.repository.save(newAccount);

        return entity;
    }

    public AccountDTO disable(Long id){
        Optional<Cuenta> res = this.repository.findById(id);

        if(res.isPresent()){
            Cuenta c = res.get();
            c.setAvailable(false);
            this.repository.save(c);

            return new AccountDTO(c.getNameAccount(),c.getDischargeDate(),c.getBalance(),c.getId_mercadoPago(),c.isAvailable());
        }

        throw new EntityNotFoundException("No se pudo encontrar cuenta con id: "+id);
    }

    public AccountDTO update(Long id, AccountDTO entity){
        Optional<Cuenta> c = this.repository.findById(id);

        if(c.isPresent()){
            Cuenta update= c.get();
            update.setBalance(entity.getBalance());
            update.setNameAccount(entity.getNameAccount());
            update.setDischargeDate(entity.getDischargeDate());
            update.setId_mercadoPago(entity.getId_mercadoPago());
            update.setAvailable(entity.isAvailable());

            this.repository.save(update);

            return entity;
        }

        throw new EntityNotFoundException("No se encontro cuenta con id: "+id);
    }


    public AccountDTO delete(Long id){
        Optional<Cuenta> c = this.repository.findById(id);

        if(c.isPresent()){
            Cuenta delete=c.get();
            this.repository.delete(delete);

            return new AccountDTO(delete.getNameAccount(), delete.getDischargeDate(),delete.getBalance(), delete.getId_mercadoPago(), delete.isAvailable());
        }

        throw new EntityNotFoundException("No se encontro cuenta con id: "+id);
    }

    public ResponseDebitDTO debitCredit(Long id, ResponseDebitDTO total){
        Optional<Cuenta> res = this.repository.findById(id);

        if(res.isPresent()){
            Cuenta c = res.get();
            int newTotal=(int) Math.round(c.getBalance()-total.getTotalDebit());
            if(newTotal < 0){
                throw new EntityNotFoundException("No se pudo cobrar el total por falta de saldo.");
            }
            c.setBalance(newTotal);
            this.repository.save(c);

            return total;
        }

        throw new EntityNotFoundException("No se encontro cuenta con id "+id+" y no se pudo completar el debito.");
    }

}
