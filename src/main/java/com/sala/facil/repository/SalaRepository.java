package com.sala.facil.repository;

import com.sala.facil.entity.Sala;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface SalaRepository extends JpaRepository<Sala, Long> {


}
