package com.example.demo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.modelo.Servicio;

import java.util.List;

public interface ServicioRepository extends MongoRepository<Servicio, String> {

    List<Servicio> findDistinctBy();
}