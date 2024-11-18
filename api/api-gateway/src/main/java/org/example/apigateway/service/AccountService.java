package org.example.apigateway.service;

import org.example.apigateway.DTO.account.DisableAccountDTO;
import org.example.apigateway.DTO.account.ResponseAccountDTO;
import org.example.apigateway.feignClient.PaymentClient;
import org.example.apigateway.model.Cuenta;
import org.example.apigateway.repository.AccountRepository;
import org.example.apigateway.security.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;
    @Autowired
    PaymentClient paymentClient;


    public List<Cuenta> findAll(){
        return this.repository.findAll();
    }


    public DisableAccountDTO disable(Long id){
        Optional<Cuenta> res = this.repository.findById(id);

        if(res.isEmpty()){
            throw new RuntimeException("No se encontro cuenta con id: "+id);
        }

        Cuenta c = res.get();
        c.setAvailable(false);

        this.repository.save(c);

        return new DisableAccountDTO(c.getUsername(),c.getEmail(),c.isAvailable());
    }

    public ResponseAccountDTO delete(Long id){
        Optional<Cuenta> res = this.repository.findById(id);
        if(res.isEmpty()){
            throw new RuntimeException("No se encontro cuenta con id: "+id);
        }

        Cuenta c = res.get();



        if(Objects.equals(c.getRole().getType(), Constants.client)){
            if(this.deletePayment(id)){
                this.repository.delete(c);
                return new ResponseAccountDTO(c.getUsername(),c.getEmail());
            }else{
                throw new RuntimeException("Fallo desvincular el medio de pago de cuenta.");
            }
        }else{
            this.repository.delete(c);
        }

        return new ResponseAccountDTO(c.getUsername(),c.getEmail());
    }

    private boolean deletePayment(Long id){
        ResponseEntity<?> res = this.paymentClient.delete(id);

        if(res.getStatusCode().is2xxSuccessful() && res.getBody() != null){
            return true;
        }

        return false;
    }
}