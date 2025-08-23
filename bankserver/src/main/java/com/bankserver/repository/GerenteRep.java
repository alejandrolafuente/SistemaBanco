package com.bankserver.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankserver.model.Gerente;

@Repository
public interface GerenteRep extends JpaRepository<Gerente, Long> {

    // encontra gerentes ordenados pela quantidade de contas
    @Query("SELECT g FROM Gerente g ORDER BY SIZE(g.contasGerenciadas) ASC")
    List<Gerente> findAllOrderByQuantidadeContas(Pageable pageable);
}
