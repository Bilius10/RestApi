package com.sala.facil.repository;

import com.sala.facil.entity.Reserva;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r.usuario_id FROM Reserva r WHERE r.usuario_id = :id_usuario and r.status = 1 and r.data_pedido = :data ")
    List<Integer> findByusuario_idAndstatus(int id_usuario, LocalDateTime data);

    @Query("SELECT r.usuario_id FROM Reserva r WHERE r.sala_id = :id AND r.data_pedido = :data AND r.status = 1")
    List<Integer> findByData_pedido(LocalDateTime data, int id);

    @Modifying
    @Transactional
    @Query("UPDATE Reserva SET data_pedido = :data_pedido, data_reserva = :data_reserva, sala_id = :id_sala, usuario_id = :usuario_id WHERE id_reserva = :id_reserva")
    int updateById_reserva(LocalDateTime data_pedido, LocalDateTime data_reserva, int id_sala, int usuario_id, long id_reserva);
}
