package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modelo.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    List<Cliente> findDistinctBy();
}
