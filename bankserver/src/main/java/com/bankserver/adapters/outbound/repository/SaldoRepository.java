package com.bankserver.adapters.outbound.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaSaldoEntidade;

@Repository
public interface SaldoRepository extends JpaRepository<JpaSaldoEntidade, Long> {

    List<JpaSaldoEntidade> findByContaIdAndDataBetween(Long contaId, LocalDate inicio, LocalDate fim);

    Optional<JpaSaldoEntidade> findTopByContaIdOrderByDataDesc(Long contaId);
}
