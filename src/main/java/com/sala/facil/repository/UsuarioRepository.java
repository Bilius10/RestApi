package com.sala.facil.repository;

import com.sala.facil.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
