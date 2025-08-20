package com.bankserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Administrador;

@Repository
public interface AdministradorRep extends JpaRepository<Administrador, Long> {

}
