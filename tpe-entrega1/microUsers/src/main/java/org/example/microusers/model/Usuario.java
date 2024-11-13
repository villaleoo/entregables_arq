package org.example.microusers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long id_user;
    @Column(name="nombre")
    private String name;
    @Column(name="apellido")
    private String surname;
    @Column(name="celular")
    private String phone;
    private String email;
    @Column(name="fecha_registro")
    private LocalDate registrationDate;
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol role;
    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta account;


    public Usuario() {}


    public Usuario(String name, String surname, String phone, String email, LocalDate registrationDate, Rol role) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
        this.role=role;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_user=" + id_user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                '}';
    }

}
