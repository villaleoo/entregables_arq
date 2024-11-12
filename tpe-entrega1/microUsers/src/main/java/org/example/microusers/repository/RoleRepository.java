package org.example.microusers.repository;

import org.example.microusers.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Rol,Long> {

    @Query("SELECT r FROM Rol r WHERE r.type=:type")
    Optional<Rol> findByType(String type);
}
