package com.example.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tipos")
public class TipoHabitacion {

    @Id
    private String id;

    private String nombre;

    // Constructors, getters, and setters

    public TipoHabitacion() {
        // Default constructor
    }

    public TipoHabitacion(String nombre) {
        this.nombre = nombre;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
