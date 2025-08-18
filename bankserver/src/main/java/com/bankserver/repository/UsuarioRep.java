package com.bankserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Usuario;

@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long> {

    boolean existsByLogin(String login);

    Optional<Usuario> findByLogin(String login);
}
