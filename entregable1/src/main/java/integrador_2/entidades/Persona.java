package integrador_2.entidades;

import javax.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    protected Integer id;
    protected String nombre;
    protected String apellido;
    protected Integer edad;
    protected String genero;
    protected Integer dni;
    protected String ciudad_residencia;

    public Persona() {
        super();
    }

    public Persona(Integer id, String nombre, String apellido, Integer edad, String genero, Integer dni,
                   String ciudad_residencia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad_residencia = ciudad_residencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }
}
