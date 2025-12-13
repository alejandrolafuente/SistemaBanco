package com.bankserver.adapters.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaUsuarioEntidade;
//import com.bankserver.application.domain.Usuario;

@Repository
public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntidade, Long> {

    boolean existsByLogin(String login);

    boolean existsByCpf(String cpf);

    //Optional<Usuario> findByLogin(String login);

    Optional<JpaUsuarioEntidade> findByLogin(String login);
}
