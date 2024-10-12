package com.sala.facil.controller;

import com.sala.facil.entity.Usuario;
import com.sala.facil.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/all")
    public List<Usuario> getAllUser(){
        return service.findAll();
    }

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        return service.saveUsuario(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable long id){
        Optional<Usuario> usuario = service.findById(id);

        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteByID(@PathVariable long id){
        Optional<Usuario> usuario = service.deleteById(id);

        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
