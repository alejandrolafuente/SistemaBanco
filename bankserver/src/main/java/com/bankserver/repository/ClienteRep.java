package com.bankserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Cliente;

@Repository
public interface ClienteRep extends JpaRepository<Cliente, Long> {

}
