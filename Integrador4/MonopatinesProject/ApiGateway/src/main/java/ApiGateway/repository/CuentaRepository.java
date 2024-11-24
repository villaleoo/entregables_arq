package ApiGateway.repository;

import ApiGateway.Entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query("""
                FROM Cuenta c JOIN FETCH c.roles
                WHERE lower(c.email) =  ?1
            """)
    Optional<Cuenta> findOneWithAuthoritiesByEmailIgnoreCase(String email);
}
