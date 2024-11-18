package org.example.apigateway.repository;

import jakarta.transaction.Transactional;
import org.example.apigateway.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Cuenta,Long> {

    @Query("SELECT c FROM Cuenta c WHERE c.username =:username OR c.email=:username")
    Optional<Cuenta> findByUserName(String username);
}
