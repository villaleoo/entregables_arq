package org.example.microusers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

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
    private Date registrationDate;
    @Column(name = "id_cuenta", nullable = false)
    private long id_account;


    public Usuario() {}


    public Usuario(String name, String surname, String phone, String email, Date registrationDate) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
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
                '}';
    }

}
