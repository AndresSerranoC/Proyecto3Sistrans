package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Cliente;
import com.example.demo.repositorio.ClienteRepository;

import java.util.List;

@Controller
@RequestMapping("/llegadaClientes")
public class LlegadaClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String getAllLlegadaClientes(Model model) {
        // List<Cliente> clientes = clienteRepository.findAll();
        // model.addAttribute("clientes", clientes);
        return "llegadaClientes";
    }

}
