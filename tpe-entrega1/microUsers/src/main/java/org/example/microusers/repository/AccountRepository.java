package org.example.microusers.repository;

import org.example.microusers.DTO.AccountDTO;
import org.example.microusers.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Cuenta,Long> {

    @Query("SELECT new org.example.microusers.DTO.AccountDTO(c.nameAccount,c.dischargeDate,c.balance,c.id_mercadoPago,c.isAvailable) FROM Cuenta c")
    List<AccountDTO> findAllProtected();

    @Query("SELECT new org.example.microusers.DTO.AccountDTO(c.nameAccount,c.dischargeDate,c.balance,c.id_mercadoPago,c.isAvailable) FROM Cuenta c WHERE c.id_account = :id")
    AccountDTO findByIdProtected(Long id);
}
