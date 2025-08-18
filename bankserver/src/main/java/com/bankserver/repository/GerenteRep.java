package com.bankserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Gerente;

@Repository
public interface GerenteRep extends JpaRepository<Gerente, Long> {

}
