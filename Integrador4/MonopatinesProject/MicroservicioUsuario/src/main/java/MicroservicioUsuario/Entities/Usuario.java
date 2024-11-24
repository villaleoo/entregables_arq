package MicroservicioUsuario.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String nroCelular;

    @Column
    private String email;

    @Column
    private Date fechaAlta= Date.from(Instant.now());

    @Column(nullable = false)
    private Long idCuenta;
}
