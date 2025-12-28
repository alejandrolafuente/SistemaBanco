package com.bankserver.adapters.outbound.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.entidades.JpaGerenteEntidade;

@Repository
public interface JpaGerenteRepository extends JpaRepository<JpaGerenteEntidade, Long> {

    // encontra gerentes ordenados pela quantidade de contas
    @Query("SELECT g FROM JpaGerenteEntidade g ORDER BY SIZE(g.contasGerenciadas) ASC")
    List<JpaGerenteEntidade> findAllOrderByQuantidadeContas(Pageable pageable);
}
