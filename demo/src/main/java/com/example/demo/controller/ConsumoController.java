package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.modelo.Consumo;
import com.example.demo.modelo.Habitacion;
import com.example.demo.modelo.TipoHabitacion;
import com.example.demo.repositorio.ConsumoRepository;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping
    public String getAllConsumos(Model model) {
        List<Consumo> consumos = consumoRepository.findAll();
        model.addAttribute("consumos", consumos);
        return "consumos";
    }

    @PutMapping("/updateConsumo/{id}")
    @ResponseBody
    public ResponseEntity<String> updateConsumo(@PathVariable String id, @RequestParam String fecha,
            @RequestParam String descripcion, @RequestParam String cantidad, @RequestParam String total) {
        try {
            Consumo consumo = consumoRepository.findById(id).orElse(null);

            if (consumo != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                consumo.setFecha(dateFormat.parse(fecha));
                consumo.setDescripcion(descripcion);
                consumo.setCantidad(Integer.parseInt(cantidad));
                consumo.setTotal(Double.parseDouble(total));
                consumoRepository.save(consumo);
                return ResponseEntity.ok("Name updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating name");
        }
    }

    @PostMapping("/createConsumo")
    @ResponseBody
    public ResponseEntity<String> createConsumo(@RequestParam String fecha,
            @RequestParam String descripcion, @RequestParam String cantidad, @RequestParam String total) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Consumo consumo = new Consumo();
            consumo.setFecha(dateFormat.parse(fecha));
            consumo.setDescripcion(descripcion);
            consumo.setCantidad(Integer.parseInt(cantidad));
            consumo.setTotal(Double.parseDouble(total));

            consumoRepository.save(consumo);

            return ResponseEntity.ok("Consumo created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating consumo");
        }
    }

    @DeleteMapping("/deleteConsumo/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteConsumo(@PathVariable String id) {
        try {
            Consumo consumo = consumoRepository.findById(id).orElse(null);

            if (consumo != null) {
                consumoRepository.deleteById(id);

                return ResponseEntity.ok("Consumo deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting consumo");
        }
    }

}
