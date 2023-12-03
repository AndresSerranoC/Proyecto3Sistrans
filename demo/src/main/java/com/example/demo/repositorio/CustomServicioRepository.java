package com.example.demo.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Consumo;
import com.example.demo.modelo.Servicio;

import java.util.List;

@Repository
public class CustomServicioRepository {

    private final MongoTemplate mongoTemplate;

    public CustomServicioRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Servicio> findDistinctServicios() {
        return mongoTemplate.query(Consumo.class)
                .distinct("servicios")
                .as(Servicio.class)
                .all();
    }
}
