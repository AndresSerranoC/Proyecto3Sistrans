package com.example.demo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modelo.Consumo;

public interface ConsumoRepository extends MongoRepository<Consumo, String> {
}
