package org.example.microusers.repository;

import org.example.microusers.DTO.user.UserDTO;
import org.example.microusers.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Usuario,Long> {
}
