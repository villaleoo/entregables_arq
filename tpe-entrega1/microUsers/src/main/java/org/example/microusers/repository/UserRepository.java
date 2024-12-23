package org.example.microusers.repository;

import org.example.microusers.DTO.client.ClientDTO;
import org.example.microusers.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Usuario,Long> {


    @Query("SELECT new org.example.microusers.DTO.client.ClientDTO(u.name,u.surname,u.phone,u.email,u.registrationDate,u.role.type) FROM Usuario u")
    List<ClientDTO> findAllProtected();
}
