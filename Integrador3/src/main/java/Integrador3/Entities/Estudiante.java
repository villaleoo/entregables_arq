package Integrador3.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Estudiante {

    @Id
    private Long documento;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column
    private String ciudad;

    @Column(unique = true)
    private int nroLibreta;

    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> carreras;

    public Estudiante(Long documento, String nombre, String apellido, int edad, String genero, String ciudad, int nroLibreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.documento = documento;
        this.ciudad = ciudad;
        this.nroLibreta = nroLibreta;
        this.carreras = new ArrayList<>();
    }

    public void addCarrera(Inscripcion carrera) {
        this.carreras.add(carrera);
    }


    @Override
    public boolean equals(Object o) {
        Estudiante otro = (Estudiante) o;
        return this.documento == otro.getDocumento() && this.nroLibreta == otro.getNroLibreta();
    }


    @Override
    public String toString() {
        return "Estudiante{" +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", documento=" + documento +
                ", ciudad='" + ciudad + '\'' +
                ", nroLibreta=" + nroLibreta +
                '}';
    }
}
