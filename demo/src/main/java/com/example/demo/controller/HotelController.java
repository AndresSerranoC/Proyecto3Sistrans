package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repositorio.HabitacionRepository;

@Controller
@RequestMapping("")

public class HotelController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/hotel")
    public String home() {
        return "index";
    }

    @GetMapping("/requerimientosFuncionales")
    public String requerimientosFuncionales() {
        return "requerimientosFuncionales";
    }
}
