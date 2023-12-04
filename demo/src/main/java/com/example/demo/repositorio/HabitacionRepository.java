package com.example.demo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.modelo.Habitacion;

import java.util.List;

public interface HabitacionRepository extends MongoRepository<Habitacion, String> {

    List<Habitacion> findDistinctBy();

    List<Habitacion> findByTipo(String tipoId);

    @Query("{" +
            "'consumo': {" +
            "$elemMatch: {" +
            "'fecha': {" +
            "$gte: ?0, $lt: ?1" +
            "}" +
            "}" +
            "}" +
            "}")
    List<Habitacion> findHabitacionesWithConsumosBetweenDates(String fromDate, String toDate);
}
