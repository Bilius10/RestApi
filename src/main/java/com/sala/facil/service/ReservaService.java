package com.sala.facil.service;

import com.sala.facil.Exceptions.RegraNegocioException;
import com.sala.facil.entity.Reserva;
import com.sala.facil.entity.Sala;
import com.sala.facil.entity.Usuario;
import com.sala.facil.repository.ReservaRepository;
import com.sala.facil.repository.SalaRepository;
import com.sala.facil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public Reserva createReserva (Reserva reserva, Long idSala, Long idUsuario) throws RegraNegocioException {

        //checar usuario existe
        Optional<Usuario> usuario = repositoryUsuario.findById(idUsuario);
        if(usuario.isEmpty()){
            throw new RegraNegocioException("Usuario não existe");
        }
        reserva.setUsuario(usuario.get());

        //checar sala existe e esta ativa
        Optional<Sala> byIdSala = repositorySala.findById_sala(idSala);
        if(byIdSala.isEmpty()){
            throw new RegraNegocioException("Sala não existe ou desativa");
        }
        reserva.setSala(byIdSala.get());

        //checar se a data ja passou
        LocalDateTime localDateTime  = LocalDateTime.now();
        if(reserva.getData_pedido().isBefore(localDateTime)) {
            throw new RegraNegocioException("Data reservada ja passou");
        }

        //checar 30 dias de antecendia
        long diasEntre = ChronoUnit.DAYS.between(reserva.getData_reserva(), reserva.getData_pedido());
        if(diasEntre > 30){
            throw new RegraNegocioException("Seu pedido exceu o 30 dias de antecedencia");
        }

        //checar se outro usuario ja reservou a sala
        List<Integer> byDataPedido = repository.findFirstByData_pedido(reserva.getData_pedido());
        if(!byDataPedido.isEmpty()){
            throw new RegraNegocioException("Essa sala ja possui uma reserva nesse dia");
        }

        //checar se o usuario ja possui reservas
        List<Integer> byusuarioId = repository.findFirstByidUsuario(reserva.getUsuario().getId_usuario());
        if(!byusuarioId.isEmpty()){
            throw new RegraNegocioException("Usuario ja possui reservas ativas");
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

    public Optional<Reserva> atualizarReserva(Long id, Reserva reservaNova){

        Optional<Reserva> byId = repository.findById(id);

        if(byId.isEmpty()){
            return byId;
        }


        return Optional.of(repository.save(reservaNova));
    }

    public List<Reserva> usuarioReservas(Long userID){

        return repository.findByidUsuario(userID);
    }

    public List<Reserva> salaReservas(Long salaID){

        return repository.findByidSala(salaID);
    }

}
