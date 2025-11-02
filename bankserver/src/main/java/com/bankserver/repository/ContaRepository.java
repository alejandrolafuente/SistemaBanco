package com.bankserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT c FROM Conta c " +
            "WHERE c.gerente.id = :gerenteId " +
            "AND c.statusConta = 'PENDENTE' " +
            "AND c.cliente IS NOT NULL")
    List<Conta> findContasPendentesByGerenteId(@Param("gerenteId") Long gerenteId);

    Optional<Conta> findByNumeroConta(String contaDestino);

}
