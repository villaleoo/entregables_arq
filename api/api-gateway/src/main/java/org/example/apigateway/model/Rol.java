package org.example.apigateway.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
    @Column(name="tipo")
    private String type;

    public Rol() {}

    public Rol(String type) {
        this.type = type;
    }


}