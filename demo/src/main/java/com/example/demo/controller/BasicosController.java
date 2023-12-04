package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modelo.Consumo;
import com.example.demo.modelo.Habitacion;
import com.example.demo.repositorio.HabitacionRepository;

@Controller
@RequestMapping("/basicos")
public class BasicosController {
    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping
    public String getBasicos(Model model) {
        return "basicos";
    }

    @GetMapping("/dineroRecolectado/form")
    public String dineroRecolectadoForm() {
        return "dineroRecolectadoForm";
    }

    @GetMapping("/dineroRecolectado")
    public String dineroRecolectado(Model model, @RequestParam String fecha1, @RequestParam String fecha2) {
        List<Habitacion> habitaciones = habitacionRepository.findHabitacionesWithConsumosBetweenDates(
                fecha1, fecha2);
        model.addAttribute("habitaciones", habitaciones);

        return "dineroRecolectado";
    }

}
