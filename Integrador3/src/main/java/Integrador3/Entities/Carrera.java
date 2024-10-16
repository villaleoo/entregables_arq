package Integrador3.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrera;

    @Column(nullable = false)
    private String nombreCarrera;

    public  Carrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
}
