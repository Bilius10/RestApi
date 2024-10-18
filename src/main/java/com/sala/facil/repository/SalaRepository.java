package com.sala.facil.repository;

import com.sala.facil.entity.Sala;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface SalaRepository extends JpaRepository<Sala, Long> {

    @Modifying
    @Query("UPDATE Sala s SET s.nome = :nome, s.departamento = :departamento, s.descricao = :descricao, s.status = :status WHERE s.id_sala = :id")
    int  updateSalaById(String nome, String departamento, String descricao, Boolean status, Long id);
}
