package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Consumo;
import com.example.demo.repositorio.ConsumoRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<Consumo> getConsumoById(@PathVariable String id) {
        return consumoRepository.findById(id)
                .map(consumo -> ResponseEntity.ok().body(consumo))
                .orElse(ResponseEntity.notFound().build());
    }
}
