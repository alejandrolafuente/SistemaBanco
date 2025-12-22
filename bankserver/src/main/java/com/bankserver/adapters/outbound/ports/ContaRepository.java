package com.bankserver.adapters.outbound.ports;

import java.util.Optional;

import com.bankserver.application.domain.Conta;

public interface ContaRepository {

    Conta save(Conta conta);

    Conta update(Conta conta);

    Optional<Conta> findById(Long contaId);
}
