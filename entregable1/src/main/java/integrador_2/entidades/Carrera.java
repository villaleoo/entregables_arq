package integrador_2.entidades;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carrera")
public class Carrera {
    @Id
    private Integer id;
    private String nombre;
    private Integer anios_duracion;
    @OneToMany(mappedBy="carrera")
    private List<Inscripcion> estudiantes;

    public Carrera() {
        super();
    }

    public Carrera(Integer id, String nombre, Integer anios_duracion) {
        this.id = id;
        this.nombre = nombre;
        this.anios_duracion = anios_duracion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnios_duracion() {
        return anios_duracion;
    }

    public void setAnios_duracion(Integer anios_duracion) {
        this.anios_duracion = anios_duracion;
    }

}
