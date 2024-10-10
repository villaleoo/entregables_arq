package org.example.integrador_3.repositorio;

import org.example.integrador_3.persistencia.DTO.EstudianteDto;
import org.example.integrador_3.persistencia.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {
    @Query("select new org.example.integrador_3.persistencia.DTO.EstudianteDto(e.nombre, e.apellido, e.genero, e.ciudad_residencia, e.dni, e.nro_libreta) from Estudiante e where e.nro_libreta = :nro_libreta")
    public EstudianteDto traerEstudiantePorNroLibreta(Integer nro_libreta);

    @Query("select new org.example.integrador_3.persistencia.DTO.EstudianteDto(e.nombre, e.apellido, e.genero, e.ciudad_residencia, e.dni, e.nro_libreta) from Estudiante e where e.genero = :genero")
    public List<EstudianteDto> traerEstudiantesPorGenero(String genero);

    String query = "select e from Estudiante e\n" +
            "join Inscripcion i \n" +
            "on e.id=i.estudiante.id\n" +
            "where e.ciudad_residencia=:ciudad\n" +
            "and i.carrera.id = (select c.id \n" +
            "                    from Carrera c\n" +
            "                    where nombre = :carrera)";
    @Query(query)
    public List<EstudianteDto> filtrarCarreraCiudad(String carrera, String ciudad);

}
