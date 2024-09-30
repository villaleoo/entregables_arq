package integrador_2.DTO;

import integrador_2.entidades.Inscripcion;


import java.util.ArrayList;
import java.util.List;

public class CarreraDto {

    private Integer id;
    private String nombre;
    private Integer anios_duracion;
    private Integer cantidad_estudiantes;
    private List<EstudianteDto> estudiantes;

    public CarreraDto() {
        super();
    }

    public CarreraDto(Integer id, String nombre, Integer anios_duracion) {
        this.id = id;
        this.nombre = nombre;
        this.anios_duracion = anios_duracion;
        this.estudiantes = new ArrayList<EstudianteDto>();
    }

    public CarreraDto(Integer id, String nombre, Integer anios_duracion, Integer cant) {
        this.id = id;
        this.nombre = nombre;
        this.anios_duracion = anios_duracion;
        this.cantidad_estudiantes = cant;
        this.estudiantes = new ArrayList<EstudianteDto>();
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getAnios_duracion() {
        return anios_duracion;
    }

    public List<EstudianteDto> estudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<EstudianteDto> estuds) {
        this.estudiantes = estuds;
    }

    @Override
    public String toString() {
        return
                STR."id = \{id}, nombre = '\{nombre}', anios_duracion = \{anios_duracion}, \n\{estudiantes}\n";
    }

    public String mostrarPorCantidadEstudiantes() {
        return
                STR."id=\{id}, nombre='\{nombre}', anios_duracion=\{anios_duracion}, cantidad_estudiantes=\{cantidad_estudiantes}";
    }
}
