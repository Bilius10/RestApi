package com.sala.facil.service;


import com.sala.facil.DTOS.ReservaDTO;
import com.sala.facil.entity.Reserva;
import com.sala.facil.entity.Sala;
import com.sala.facil.entity.Usuario;
import com.sala.facil.repository.ReservaRepository;
import com.sala.facil.repository.SalaRepository;
import com.sala.facil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService{

    @Autowired
    public ReservaRepository repository;
    @Autowired
    public UsuarioRepository repositoryUsuario;
    @Autowired
    public SalaRepository repositorySala;

    public List<Reserva> findAll(){
        return repository.findAll();
    }

    public Reserva createReserva (Reserva reserva, ReservaDTO reservaDTO) {

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

    public Optional<Reserva> atualizarReserva(Long id, Reserva reservaNova){

        Optional<Reserva> byId = repository.findById(id);

        if(byId.isEmpty()){
            return byId;
        }


        return Optional.of(repository.save(reservaNova));
    }
}
