package com.sala.facil.service;

import com.sala.facil.entity.Sala;
import com.sala.facil.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository repository;

    public List<Sala> findAll(){
        return repository.findAll();
    }

    public Sala saveSala(Sala sala){
        return repository.save(sala);
    }

    public Optional<Sala> findByID(long ID){
        return repository.findById(ID);
    }

    public Optional<Sala> deleteByID(long id){
        Optional<Sala> sala = repository.findById(id);

        if(sala.isPresent()){
            repository.deleteById(id);
        }

        return sala;
    }

    public Optional<Sala> atualizarSala(Sala salaNova, long id){

        Optional<Sala> byId = repository.findById(id);

        if(byId.isEmpty()){
            return byId;
        }

        int rowsUpdated  = repository.updateSalaById(salaNova.getNome(), salaNova.getDepartamento(),
                salaNova.getDescricao(), salaNova.isStatus(), salaNova.getId_sala());

        return Optional.of(salaNova);
    }
}
