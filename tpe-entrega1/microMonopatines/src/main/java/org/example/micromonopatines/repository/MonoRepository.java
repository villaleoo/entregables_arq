package org.example.micromonopatines.repository;

import org.example.micromonopatines.DTO.MonoDTO;
import org.example.micromonopatines.model.Monopatin;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Map;

public interface MonoRepository extends MongoRepository<Monopatin,String> {

    @Query(value = "{}",fields = "{'_id': 0}")
    List<MonoDTO> findAllProtected();

    // Contar los monopatines disponibles
    @Query("{ 'disponible': true }")
    List<Monopatin> countAvailable();

    // Contar los monopatines no disponibles
    @Query("{ 'disponible': false }")
    List<Monopatin> countNotAvailable();
}
