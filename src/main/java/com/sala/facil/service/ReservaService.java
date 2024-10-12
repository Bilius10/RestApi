package com.sala.facil.service;

import com.sala.facil.entity.Reserva;
import com.sala.facil.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    public ReservaRepository repository;

    public List<Reserva> findAll(){
        return repository.findAll();
    }
}
