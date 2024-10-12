package com.sala.facil.service;

import com.sala.facil.entity.Reserva;
import com.sala.facil.entity.Usuario;
import com.sala.facil.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    public ReservaRepository repository;

    public List<Reserva> findAll(){
        return repository.findAll();
    }

    public Reserva createReserva(Reserva reserva){

        return repository.save(reserva);
    }

    public Optional<Reserva> findByID(long ID){
        return repository.findById(ID);
    }

    public Optional<Reserva> deleteByID(long ID){
        Optional<Reserva> reserva = repository.findById(ID);

        if(reserva.isPresent()){
            repository.deleteById(ID);
        }

        return reserva;
    }
}
