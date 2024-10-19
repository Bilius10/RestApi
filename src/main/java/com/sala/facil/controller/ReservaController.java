package com.sala.facil.controller;

import com.sala.facil.DTOS.ReservaDTO;
import com.sala.facil.entity.Reserva;
import com.sala.facil.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "reserva")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> createReserva(@RequestBody @Valid ReservaDTO reservaDTO)  {

        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);
        reserva.setStatus(reservaDTO.status() == 1);

        Reserva reserva1 = service.createReserva(reserva, reservaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(reserva1);


    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Optional<Reserva> reserva = service.findByID(id);

        if(reserva.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reserva);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<String> deleteByID(@PathVariable Long id){
        Optional<Reserva> reserva = service.deleteByID(id);

        if(reserva.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Object> atualizarReserva(@PathVariable Long id,@RequestBody @Valid ReservaDTO reservaDTO){

        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);

        reserva.setStatus(reservaDTO.status() == 1);

        Optional<Reserva> rowAffected = service.atualizarReserva(id,reserva);

        if(rowAffected.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rowAffected);
    }
}
