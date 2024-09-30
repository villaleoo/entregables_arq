package integrador_2.DTO;

import integrador_2.entidades.Carrera;
import integrador_2.entidades.Inscripcion;

import java.util.ArrayList;
import java.util.List;

public class EstudianteDto {

    private String nombre, apellido, genero, ciudad_residencia;
    private Integer dni, nro_libreta;
    private List<CarreraDto> carreras;
    private Integer graduacion;
    public EstudianteDto() {
        super();
    }

    public EstudianteDto(String nombre, String apellido, String genero, String ciudad_residencia,
                         Integer dni, Integer nro_libreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.ciudad_residencia = ciudad_residencia;
        this.dni = dni;
        this.nro_libreta = nro_libreta;
        this.carreras = new ArrayList<>();
    }

    public EstudianteDto(String nombre, String apellido, String genero, String ciudad_residencia,
                         Integer dni, Integer nro_libreta, Integer graduacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.ciudad_residencia = ciudad_residencia;
        this.dni = dni;
        this.nro_libreta = nro_libreta;
        this.graduacion = graduacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGenero() {
        return genero;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public Integer getDni() {
        return dni;
    }

    public List<CarreraDto> getCarreras() {
        return carreras;
    }

    public Integer getNro_libreta() {
        return nro_libreta;
    }

    public void agregarCarrera(CarreraDto c) {
        this.carreras.add(c);
    }

    @Override
    public String toString() {
        return
                STR."   \{nombre}, \{apellido}, \{genero}, \{ciudad_residencia}, \{dni}, \{nro_libreta}, graduado en \{graduacion}\n";
    }

    public String info() {
        return
                STR."nombre='\{nombre}', apellido='\{apellido}', genero='\{genero}', ciudad_residencia='\{ciudad_residencia}', dni=\{dni}, nro_libreta=\{nro_libreta}, graduacion=\{graduacion}";
    }
}
