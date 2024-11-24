package ApiGateway.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private Long idCuentaMP;

    @Column
    private Date fechaAlta = Date.from(Instant.now());


    @Column()
    private Boolean habilitada = true;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "cuenta_rol",
            joinColumns = {@JoinColumn(name = "cuenta_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tipo_rol", referencedColumnName = "tipo")}
    )
    private Set<Rol> roles = new HashSet<>();

    public Cuenta(final String email) {
        this.email = email.toLowerCase();
    }

    public void setAuthorities(final Collection<Rol> roles) {
        this.roles = new HashSet<>(roles);
    }
}
