package com.bankserver.adapters.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.application.domain.Administrador;

@Repository
public interface AdministradorRep extends JpaRepository<Administrador, Long> {

}
