package org.example.entregable3.repository;

import org.example.entregable3.model.Estudiante;
import org.example.entregable3.utils.enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {

    @Query("SELECT e FROM Estudiante e WHERE e.num_libreta = :num")
    public Estudiante findByNumeroLibreta(String num);

    @Query("SELECT e FROM Estudiante e WHERE e.genero = :g")
    public List<Estudiante> findAllByGenero(Genero g);

    @Query("SELECT e FROM Estudiante e JOIN e.carreras_inscriptas i WHERE e.ciudad_residencia =:ciudad AND i.carrera.id_carrera= :id_carrera")
    public List<Estudiante> findAllByCiudadAndCarrera(String ciudad , Integer id_carrera);

    @Query("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
    public List<Estudiante> findAllBySortAlpabheticSurname();

    @Query("SELECT e FROM Estudiante e WHERE e.dni =:dni")
    public Estudiante findByDni(Integer dni);
}
