package com.sala.facil.service;

import com.sala.facil.Exceptions.*;
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
    public SalaRepository repositorySala;
    @Autowired
    public UsuarioRepository repositoryUsuario;

    public List<Reserva> findAll(){
        return repository.findAll();
    }

    public Reserva createReserva (Reserva reserva) throws UsuarioJaPossuiReservaException, SalaEstaDesativada, DataDaReservaJaPassou, DataAtingiuPrazo, JaExisteReservaNesseDia, UsuarioNaoExiste, SalaNaoExiste {

        //verifica se a data da reserva ja passou
        LocalDateTime dataAtual = LocalDateTime.now();
        if(reserva.getData_pedido().isBefore(dataAtual)){
            throw new DataDaReservaJaPassou("A data que foi marcado a reserva já passou");
        }

        //Verifica o prazo  máximo de 30 dias.
        long diferencaDias = ChronoUnit.DAYS.between(dataAtual, reserva.getData_pedido());
        if(diferencaDias > 30){
            throw new DataAtingiuPrazo("Sua reserva excedeu o prazo maximo de 30 dias de antecedencia");
        }

        //verifica se o usuario existe
        Optional<Usuario> usuarioExiste = repositoryUsuario.findById(reserva.getUsuario_id());
        if(usuarioExiste.isEmpty()){
            throw new UsuarioNaoExiste("Usuario não existe");
        }

        //Verificar se a sala existe
        Optional<Sala> salaExiste = repositorySala.findById(reserva.getSala_id());
        if(salaExiste.isEmpty()){
            throw new SalaNaoExiste("Sala não existe");
        }

        //Verificar se outro usuario ja reservou a sala nesse dia
        List<Integer> reservasNoMesmoDia = repository.findByData_pedido(reserva.getData_pedido(), reserva.getSala_id());
        if(!reservasNoMesmoDia.isEmpty()){
            throw new JaExisteReservaNesseDia("Já existe uma reserva para esse dia.");
        }

        //Verifica se o usuario ja possui reserva nesse dia
        List<Integer> usuarioReservas = repository.findByusuario_idAndstatus(reserva.getUsuario_id(), reserva.getData_pedido());
        if(!usuarioReservas.isEmpty()){
            throw new UsuarioJaPossuiReservaException("Usuario já possui "+usuarioReservas.size()+" reserva(s)");
        }

        //Verificar se a sala esta ativa
        Optional<Integer> salaAtivada = repositorySala.findById_salaAndStatus(reserva.getSala_id());
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

    public int atualizarReserva(Reserva reserva){

        Optional<Reserva> byId = repository.findById(reserva.getId_reserva());

        if(byId.isEmpty()){
            return 0;
        }

        return repository.updateById_reserva(reserva.getData_pedido(), reserva.getData_reserva(), reserva.getSala_id(), reserva.getUsuario_id(), reserva.getId_reserva());
    }
}
