package com.bankserver.adapters.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaEnderecoEntidade;

@Repository
public interface EnderecoRep extends JpaRepository<JpaEnderecoEntidade, Long> {

}
