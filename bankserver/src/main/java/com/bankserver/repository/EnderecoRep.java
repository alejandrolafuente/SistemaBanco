package com.bankserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Endereco;

@Repository
public interface EnderecoRep extends JpaRepository<Endereco, Long> {

}
