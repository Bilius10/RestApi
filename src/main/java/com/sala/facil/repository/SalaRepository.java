package com.sala.facil.repository;

import com.sala.facil.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    Optional<Sala> findById_salaAndStatus(int id, int status);
}
