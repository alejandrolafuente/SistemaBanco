package com.bankserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
