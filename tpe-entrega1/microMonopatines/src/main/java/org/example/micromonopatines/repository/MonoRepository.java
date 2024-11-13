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


    @Query("{ 'disponible': true }")
    List<Monopatin> countAvailable();


    @Query("{ 'disponible': false }")
    List<Monopatin> countNotAvailable();

    @Query("{ 'stop_assign.id_stop': ?0, 'isAvailable': true }")
    List<Monopatin> findAllInStopAndAvailable(Long id);
}
