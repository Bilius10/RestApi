package com.sala.facil.controller;

import com.sala.facil.DTOS.UsuarioDTO;
import com.sala.facil.entity.Usuario;
import com.sala.facil.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Usuario>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUsuario(usuario));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        Optional<Usuario> usuario = service.findById(id);

        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não Encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable long id){
        Optional<Usuario> usuario = service.deleteById(id);

        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
