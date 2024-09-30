package integrador_2.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "estudiante")
public class Estudiante extends Persona{
    private Integer nro_libreta;
    @OneToMany(mappedBy="estudiante")
    private List<Inscripcion> carreras;
    public Estudiante() {
        super();
    }

    public Estudiante(Integer id, String nombre, String apellido, Integer edad, String genero,
                      Integer dni, String ciudad_residencia, Integer nro_libreta) {
        super(id, nombre, apellido, edad, genero, dni, ciudad_residencia);
        this.nro_libreta = nro_libreta;
    }

    public Integer getNro_libreta() {
        return nro_libreta;
    }

    public void setNro_libreta(Integer nro_libreta) {
        this.nro_libreta = nro_libreta;
    }
}
