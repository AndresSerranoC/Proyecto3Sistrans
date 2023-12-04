package com.example.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;

    private String direccion;
    private String telefono;
    private String nombre;
    private String apellido;
    private List<Date> fechaLlegada;
    private List<Date> fechaSalida;

    private List<String> reservas;
    private List<String> consumos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<String> getReservas() {
        return reservas;
    }

    public void setReservas(List<String> reservas) {
        this.reservas = reservas;
    }

    public List<String> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<String> consumos) {
        this.consumos = consumos;
    }

    public List<Date> getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(List<Date> fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public List<Date> getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(List<Date> fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

}
