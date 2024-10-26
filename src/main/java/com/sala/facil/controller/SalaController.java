package com.sala.facil.controller;

import com.sala.facil.DTOS.SalaDTO;
import com.sala.facil.entity.Sala;
import com.sala.facil.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "salas")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping
    public ResponseEntity<List<Sala>> getAllSala(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Sala> saveSala(@RequestBody @Valid SalaDTO salaDTO){

        Sala sala = new Sala();
        BeanUtils.copyProperties(salaDTO, sala);
        sala.setStatus(salaDTO.status() == 1);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveSala(sala));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByID(@PathVariable long id){
        Optional<Sala> sala = service.findByID(id);

        if(sala.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não Encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable long id){
        try {
            Optional<Sala> sala = service.deleteByID(id);

            if (sala.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não Encontrado");
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sala excluida");
        }catch (DataIntegrityViolationException s) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sala esta inserida em uma reserva");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarSala(@PathVariable long id, @RequestBody @Valid SalaDTO salaDTO){

        Sala sala = new Sala();
        BeanUtils.copyProperties(salaDTO, sala);

        sala.setStatus(salaDTO.status() == 1);

        Optional<Sala> rowAffected = service.atualizarSala(id, sala);

        if(rowAffected.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(rowAffected);

    }

}
