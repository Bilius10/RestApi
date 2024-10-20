package com.sala.facil.repository;

import com.sala.facil.entity.Sala;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Transactional
public interface SalaRepository extends JpaRepository<Sala, Long> {

    @Query("SELECT s FROM Sala s WHERE s.id_sala = :id AND s.status = true")
    Optional<Sala> findById_sala(Long id);
}
