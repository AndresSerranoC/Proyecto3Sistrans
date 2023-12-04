package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.modelo.Habitacion;
import com.example.demo.modelo.Reserva;
import com.example.demo.modelo.TipoHabitacion;
import com.example.demo.repositorio.ReservaRepository;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public String getAllReservas(Model model) {
        List<Reserva> reservas = reservaRepository.findAll();
        model.addAttribute("reservas", reservas);
        return "reservas";
    }

    @PutMapping("/updateReserva")
    @ResponseBody
    public ResponseEntity<String> updateReserva(
            @RequestParam String id,
            @RequestParam String fechaEntrada,
            @RequestParam String fechaSalida,
            @RequestParam String numPersonas,
            @RequestParam String estado) {
        try {
            System.out.println("Received update request for Reserva with ID: " + id);

            Reserva reserva = reservaRepository.findById(id).orElse(null);

            if (reserva != null) {
                System.out.println("Reserva found. Updating...");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Parse the date strings
                reserva.setFechaEntrada(dateFormat.parse(fechaEntrada));
                reserva.setFechaSalida(dateFormat.parse(fechaSalida));

                reserva.setNumeroPersonas(Integer.parseInt(numPersonas));
                reserva.setEstado(estado);
                reservaRepository.save(reserva);

                System.out.println("Reserva updated successfully.");

                return ResponseEntity.ok("Reserva updated successfully");
            } else {
                System.out.println("Reserva not found.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating reserva");
        }
    }

    @PostMapping("/createReserva")
    @ResponseBody
    public ResponseEntity<String> createReserva(@RequestParam String fechaEntrada, @RequestParam String fechaSalida,
            @RequestParam String numPersonas, @RequestParam String estado) {
        try {
            Reserva reserva = new Reserva();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            reserva.setFechaEntrada(dateFormat.parse(fechaEntrada));
            reserva.setFechaSalida(dateFormat.parse(fechaSalida));

            reserva.setNumeroPersonas(Integer.parseInt(numPersonas));
            reserva.setEstado(estado);

            reservaRepository.save(reserva);

            return ResponseEntity.ok("Reserva created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating reserva");
        }
    }

}
