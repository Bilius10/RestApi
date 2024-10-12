package com.sala.facil.controller;

import com.sala.facil.entity.Reserva;
import com.sala.facil.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "reserva")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping("/all")
    public List<Reserva> getAllReservas(){
        return service.findAll();
    }

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva){
        return service.createReserva(reserva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable long id){
        Optional<Reserva> reserva = service.findByID(id);

        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Reserva> deleteByID(@PathVariable long id){
        Optional<Reserva> reserva = service.deleteByID(id);

        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
