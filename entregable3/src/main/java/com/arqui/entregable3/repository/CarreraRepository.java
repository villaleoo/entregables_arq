package com.arqui.entregable3.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.arqui.entregable3.model.Entities.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    /* 
    @Query("SELECT new com.arqui.entregable3.model.DTO.CarreraDTO(c.titulo, COUNT(i.carrera.id_carrera)) FROM Carrera c JOIN Inscripcion i ON c.id_carrera = i.carrera.id_carrera GROUP BY c.titulo HAVING COUNT(i.carrera.id_carrera) > 0 ORDER BY COUNT(i.carrera.id_carrera) DESC")
    List<CarreraDTO> getCarreras();
*/
}
