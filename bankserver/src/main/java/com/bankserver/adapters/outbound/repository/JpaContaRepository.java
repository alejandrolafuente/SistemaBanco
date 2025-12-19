package com.bankserver.adapters.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaContaEntidade;

@Repository
public interface JpaContaRepository extends JpaRepository<JpaContaEntidade, Long> {

    @Query("SELECT c FROM Conta c " +
            "WHERE c.gerente.id = :gerenteId " +
            "AND c.statusConta = 'PENDENTE' " +
            "AND c.cliente IS NOT NULL")
    List<JpaContaEntidade> findContasPendentesByGerenteId(@Param("gerenteId") Long gerenteId);

    Optional<JpaContaEntidade> findByNumeroConta(String contaDestino);

}
