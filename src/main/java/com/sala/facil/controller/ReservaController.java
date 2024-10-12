package com.sala.facil.controller;

import com.sala.facil.entity.Reserva;
import com.sala.facil.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(value = "reserva")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping("/all")
    public List<Reserva> getAllReservas(){
        return service.findAll();
    }
}
