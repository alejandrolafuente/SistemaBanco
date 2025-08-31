package com.bankserver.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Saldo;

@Repository
public interface SaldoRep extends JpaRepository<Saldo, Long> {

    List<Saldo> findByContaIdAndDataBetween(Long contaId, LocalDate inicio, LocalDate fim);

    Optional<Saldo> findTopByContaIdOrderByDataDesc(Long contaId);
}
