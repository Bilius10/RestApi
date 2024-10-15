package com.sala.facil.repository;

import com.sala.facil.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuario_idAndStatus(int id, int status);
}
