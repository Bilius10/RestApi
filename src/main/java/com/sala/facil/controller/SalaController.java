package com.sala.facil.controller;

import com.sala.facil.entity.Sala;
import com.sala.facil.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "sala")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping("/all")
    public List<Sala> getAllSala(){
        return service.findAll();
    }

    @PostMapping
    public Sala saveSala(@RequestBody Sala sala){
        return service.saveSala(sala);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> findByID(@PathVariable long id){
        Optional<Sala> sala = service.findByID(id);

        return sala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sala> deleteByID(@PathVariable long id){
        Optional<Sala> sala = service.deleteByID(id);

        return sala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
