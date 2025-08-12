package com.bankserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Conta;

@Repository
public interface ContaRep extends JpaRepository<Conta, Long> {

}
