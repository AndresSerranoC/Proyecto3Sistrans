package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modelo.Habitacion;
import com.example.demo.modelo.TipoHabitacion;
import com.example.demo.repositorio.HabitacionRepository;
import com.example.demo.repositorio.TipoRepository;

import java.util.List;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @GetMapping
    public String getAllHabitaciones(Model model) {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        List<TipoHabitacion> tipos = tipoRepository.findAll();
        model.addAttribute("habitaciones", habitaciones);
        model.addAttribute("tipos", tipos);

        // Fetch and add associated Tipo for each Habitacion
        habitaciones.forEach(habitacion -> {
            TipoHabitacion tipo = tipoRepository.findById(habitacion.getTipo()).orElse(null);
            if (tipo != null) {
                habitacion.setTipo(tipo.getNombre());
            } else {
                habitacion.setTipo("Tipo not found"); // Set a default value or handle as needed
            }
        });

        return "habitaciones";
    }

    @PutMapping("/updateHabitacion/{id}")
    @ResponseBody
    public ResponseEntity<String> updateHabitacion(
            @PathVariable String id,
            @RequestParam String tipo,
            @RequestParam String capacidadMaxima,
            @RequestParam String dotacion,
            @RequestParam String estado,
            @RequestParam String tarifa) {
        try {
            Habitacion habitacion = habitacionRepository.findById(id).orElse(null);

            if (habitacion != null) {
                habitacion.setTipo(tipo);
                System.out.println(tipo);
                habitacion.setCapacidadMax(Integer.parseInt(capacidadMaxima));
                habitacion.setDotacion(dotacion);
                habitacion.setEstado(estado);
                habitacion.setTarifa(Double.parseDouble(tarifa));

                habitacionRepository.save(habitacion);
                return ResponseEntity.ok("Habitacion updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating habitacion");
        }
    }

    @PostMapping("/createHabitacion")
    @ResponseBody
    public ResponseEntity<String> createHabitacion(
            @RequestParam String tipo,
            @RequestParam String capacidadMaxima,
            @RequestParam String dotacion,
            @RequestParam String estado,
            @RequestParam String tarifa) {
        try {
            // Create a new habitacion
            Habitacion nuevaHabitacion = new Habitacion();
            nuevaHabitacion.setTipo(tipo);
            nuevaHabitacion.setCapacidadMax(Integer.parseInt(capacidadMaxima));
            nuevaHabitacion.setDotacion(dotacion);
            nuevaHabitacion.setEstado(estado);
            nuevaHabitacion.setTarifa(Double.parseDouble(tarifa));

            // Save the new habitacion to the repository
            habitacionRepository.save(nuevaHabitacion);

            return ResponseEntity.ok("Habitacion created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating habitacion");
        }
    }

    @DeleteMapping("/deleteHabitacion/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteHabitacion(@PathVariable String id) {
        try {
            habitacionRepository.deleteById(id);
            return ResponseEntity.ok("Habitacion deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting habitacion");
        }
    }

}
