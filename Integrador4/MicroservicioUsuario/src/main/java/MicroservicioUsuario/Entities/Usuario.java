package MicroservicioUsuario.Entities;

import jakarta.persistence.*;
import lombok.*;

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
    private  Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String nroCelular;

    @Column
    private String email;

    @OneToMany
    private List<Cuenta> cuentas;
}
