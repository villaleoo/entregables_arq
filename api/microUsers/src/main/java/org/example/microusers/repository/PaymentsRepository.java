package org.example.microusers.repository;

import org.example.microusers.model.MediosDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PaymentsRepository extends JpaRepository<MediosDePago, UUID> {

    @Query("SELECT m FROM MediosDePago m WHERE m.id_account =:id")
    Optional<MediosDePago> findByIdAccount(Long id);
}
