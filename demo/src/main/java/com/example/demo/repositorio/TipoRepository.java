package com.example.demo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import com.example.demo.modelo.TipoHabitacion;

public interface TipoRepository extends MongoRepository<TipoHabitacion, String> {

    List<TipoHabitacion> findDistinctBy();

}
