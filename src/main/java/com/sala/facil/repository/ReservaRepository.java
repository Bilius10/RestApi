package com.sala.facil.repository;

import com.sala.facil.entity.Reserva;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ReservaRepository extends JpaRepository<Reserva, Long> {


}
