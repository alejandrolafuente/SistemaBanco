package com.bankserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Usuario;

@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);
}
