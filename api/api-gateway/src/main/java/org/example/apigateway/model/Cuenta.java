package org.example.apigateway.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cuenta")
    private Long id_account;
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol role;
    @Column(name="nombre_usuario")
    private String username;
    @Column(unique = true)
    private String email;
    @Column(name="contrasenia")
    private String password;
    @Column(name="fecha_alta")
    private Date dischargeDate;
    @Column(name="disponible")
    @JsonProperty("isAvailable")
    private boolean isAvailable;


    public Cuenta() {}

    public Cuenta(String username, String email, String password, Date dischargeDate, boolean isAvailable) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dischargeDate = dischargeDate;
        this.isAvailable=isAvailable;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "rol=" + role.getType() +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dischargeDate=" + dischargeDate +
                ", isAvailable=" + isAvailable +
                '}';
    }

}
