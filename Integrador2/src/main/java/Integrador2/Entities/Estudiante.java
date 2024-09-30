package Integrador2.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstudiante;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column(unique=true)
    private int documento;

    @Column
    private String ciudad;

    @Column(unique=true)
    private int nroLibreta;

    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> carreras;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, int edad, String genero, int documento, String ciudad, int nroLibreta) {
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

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }

    public void setNroLibreta(int nroLibreta) {
        this.nroLibreta = nroLibreta;
    }

    //  public List<Carrera> getCarreras() {
    //    return new ArrayList<>(this.carreras);
    // }

    @Override
    public boolean equals(Object o) {
        Estudiante otro = (Estudiante) o;
        return this.getIdEstudiante()==otro.getIdEstudiante() && this.documento==otro.getDocumento() && this.nroLibreta==otro.getNroLibreta();
    }


    @Override
    public String toString() {
        return "Estudiante{" +
                "idEstudiante=" + idEstudiante +
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
