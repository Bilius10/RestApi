package com.sala.facil.repository;

import com.sala.facil.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    @Query("SELECT s.status FROM Sala s WHERE s.id_sala = :id_sala AND s.status = 1")
    Optional<Integer> findById_salaAndStatus(Long id_sala);
}
