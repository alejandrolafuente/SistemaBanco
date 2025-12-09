package com.bankserver.adapters.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Usuario;

@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long> {

    boolean existsByLogin(String login);
    boolean existsByCpf(String cpf); 
    Optional<Usuario> findByLogin(String login);
}
