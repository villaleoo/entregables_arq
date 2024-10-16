package org.example.entregable3.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entregable3.utils.enums.Genero;

import java.time.LocalDate;
import java.time.Period;


@Getter
@Setter
public class EstudianteDTO {
    private Integer documento;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private Genero genero;
    private String ciudad;
    private String nroLibreta;
    private int edad;


    public EstudianteDTO(Integer documento, String nombre,String apellido,LocalDate fecha_nacimiento, Genero genero, String ciudad,String num_libreta){
        this.documento=documento;
        this.nombre=nombre;
        this.apellido=apellido;
        this.fecha_nacimiento= fecha_nacimiento;
        this.genero=genero;
        this.ciudad=ciudad;
        this.nroLibreta=num_libreta;
        this.edad=this.getEdad();
    }

    public int getEdad(){
        return Period.between(this.fecha_nacimiento, LocalDate.now()).getYears();
    }
}


