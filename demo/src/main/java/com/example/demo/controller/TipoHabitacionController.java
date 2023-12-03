package com.example.demo.controller;

import com.example.demo.modelo.Habitacion;
import com.example.demo.modelo.TipoHabitacion;
import com.example.demo.repositorio.HabitacionRepository;
import com.example.demo.repositorio.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TipoHabitacionController {

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/tiposHabitacion")
    public String getTiposHabitacion(Model model) {
        List<TipoHabitacion> tipos = tipoRepository.findDistinctBy();
        model.addAttribute("tipos", tipos);
        return "tiposHabitacion";
    }

    @PutMapping("/updateTipo/{id}")
    @ResponseBody
    public ResponseEntity<String> updateTipo(@PathVariable String id, @RequestParam String nombre) {
        try {
            // Retrieve the tipo from the repository
            TipoHabitacion tipo = tipoRepository.findById(id).orElse(null);

            if (tipo != null) {
                // Update the name
                tipo.setNombre(nombre);
                // Save the updated tipo to the repository
                tipoRepository.save(tipo);
                return ResponseEntity.ok("Name updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating name");
        }
    }

    @PostMapping("/createTipo")
    @ResponseBody
    public ResponseEntity<String> createTipo(@RequestParam String nombre) {
        try {
            // Create a new tipo
            TipoHabitacion nuevoTipo = new TipoHabitacion();
            nuevoTipo.setNombre(nombre);

            // Save the new tipo to the repository
            tipoRepository.save(nuevoTipo);

            return ResponseEntity.ok("Tipo created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating tipo");
        }
    }

    @DeleteMapping("/deleteTipo/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTipo(@PathVariable String id) {
        try {
            // Find the tipo by ID
            TipoHabitacion tipo = tipoRepository.findById(id).orElse(null);

            if (tipo != null) {
                // Find all habitaciones with the specified tipo ID
                List<Habitacion> habitaciones = habitacionRepository.findByTipo(id);

                // Delete all habitaciones with the specified tipo ID
                habitacionRepository.deleteAll(habitaciones);

                // Delete the tipo
                tipoRepository.deleteById(id);

                return ResponseEntity.ok("Tipo and associated habitaciones deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting tipo");
        }
    }

}
