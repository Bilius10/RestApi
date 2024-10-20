package com.sala.facil.repository;

import com.sala.facil.entity.Reserva;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


@Transactional
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT id_reserva FROM Reserva WHERE data_pedido = :data AND status = true ")
    List<Integer> findFirstByData_pedido(LocalDateTime data);

    @Query("SELECT id_reserva FROM Reserva WHERE usuario.id_usuario = :id AND status = true ")
    List<Integer> findFirstByidUsuario(Long id);

    @Query("SELECT r FROM Reserva r WHERE usuario.id_usuario = :id")
    List<Reserva> findByidUsuario(Long id);

    @Query("SELECT r FROM Reserva r WHERE r.sala.id_sala = :id")
    List<Reserva> findByidSala(Long id);
}
