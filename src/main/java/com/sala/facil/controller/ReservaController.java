package com.sala.facil.controller;

import com.sala.facil.DTOS.ReservaDTO;
import com.sala.facil.Exceptions.*;
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

    @GetMapping("/all")
    public ResponseEntity<List<Reserva>> getAllReservas(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createReserva(@RequestBody @Valid ReservaDTO reservaDTO)  {
    try {
        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);

        Reserva reserva1 = service.createReserva(reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body(reserva1);
    }catch (UsuarioJaPossuiReservaException u){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u.getMessage());
    }catch (SalaEstaDesativada s){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(s.getMessage());
    }catch (JaExisteReservaNesseDia j) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(j.getMessage());
    }catch (DataAtingiuPrazo d) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(d.getMessage());
    }catch (DataDaReservaJaPassou d) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(d.getMessage());
    }catch (SalaNaoExiste s){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(s.getMessage());
    }catch (UsuarioNaoExiste u){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u.getMessage());
    }

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        Optional<Reserva> reserva = service.findByID(id);

        if(reserva.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reserva);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Object> deleteByID(@PathVariable long id){
        Optional<Reserva> reserva = service.deleteByID(id);

        if(reserva.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    private ResponseEntity<Object> atualizarReserva(@RequestBody @Valid ReservaDTO reservaDTO){
        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);

        int rowsAffected = service.atualizarReserva(reserva);
        if(rowsAffected == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Reserva atualizada");
    }
}
