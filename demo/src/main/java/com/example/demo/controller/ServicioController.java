package com.example.demo.controller;

import com.example.demo.modelo.Servicio;
import com.example.demo.repositorio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String getServicios(Model model) {
        List<Servicio> servicios = servicioRepository.findDistinctBy();
        model.addAttribute("servicios", servicios);
        return "servicios";
    }

    @PutMapping("/updateServicio/{id}")
    @ResponseBody
    public ResponseEntity<String> updateServicio(@PathVariable String id,
            @RequestParam String nombre,
            @RequestParam String capacidad,
            @RequestParam String horario,
            @RequestParam String costo) {
        try {
            // Retrieve the servicio from the repository
            Servicio servicio = servicioRepository.findById(id).orElse(null);

            if (servicio != null) {
                // Update the servicio details
                servicio.setNombre(nombre);
                servicio.setCapacidad(Integer.parseInt(capacidad));
                servicio.setHorario(horario);
                servicio.setCosto(Double.parseDouble(costo));
                // Save the updated servicio to the repository
                servicioRepository.save(servicio);
                return ResponseEntity.ok("Servicio details updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating servicio details");
        }
    }

    @PostMapping("/createServicio")
    @ResponseBody
    public ResponseEntity<String> createServicio(@RequestParam String nombre,
            @RequestParam String capacidad,
            @RequestParam String horario,
            @RequestParam String costo) {
        try {
            // Create a new servicio
            Servicio nuevoServicio = new Servicio();
            nuevoServicio.setNombre(nombre);
            nuevoServicio.setCapacidad(Integer.parseInt(capacidad));
            nuevoServicio.setHorario(horario);
            nuevoServicio.setCosto(Double.parseDouble(costo));

            // Save the new servicio to the repository
            servicioRepository.save(nuevoServicio);

            return ResponseEntity.ok("Servicio created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating servicio");
        }
    }

    @DeleteMapping("/deleteServicio/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteServicio(@PathVariable String id) {
        try {
            servicioRepository.deleteById(id);
            return ResponseEntity.ok("Servicio deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting servicio");
        }
    }
}
