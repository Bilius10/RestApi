package com.sala.facil.service;

import com.sala.facil.Exceptions.SalaEstaDesativada;
import com.sala.facil.Exceptions.UsuarioJaPossuiReservaException;
import com.sala.facil.entity.Reserva;
import com.sala.facil.entity.Sala;
import com.sala.facil.repository.ReservaRepository;
import com.sala.facil.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService{

    @Autowired
    public ReservaRepository repository;
    public SalaRepository repositorySala;

    public List<Reserva> findAll(){
        return repository.findAll();
    }

    public Reserva createReserva (Reserva reserva) throws UsuarioJaPossuiReservaException, SalaEstaDesativada {

        List<Reserva> usuarioReservas = repository.findByUsuario_idAndStatus(reserva.getUsuario_id(), 1);
        if(!usuarioReservas.isEmpty()){
            throw new UsuarioJaPossuiReservaException("Usuario possui "+usuarioReservas.size()+" reservas");
        }

        Optional<Sala> salaAtivada = repositorySala.findById_salaAndStatus(reserva.getSala_id(), 1);
        if(salaAtivada.isEmpty()){
            throw new SalaEstaDesativada("Sala esta desativada");
        }

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
