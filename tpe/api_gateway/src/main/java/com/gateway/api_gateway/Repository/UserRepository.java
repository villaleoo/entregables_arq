package com.gateway.api_gateway.Repository;

import com.gateway.api_gateway.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        FROM User u JOIN FETCH u.authorities
        WHERE lower(u.username) =  ?1
    """)
    Optional<User> findOneWithAuthoritiesByUsernameIgnoreCase(String username );
}
