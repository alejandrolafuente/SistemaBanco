package com.bankserver.adapters.outbound.ports;

import com.bankserver.application.domain.Conta;

public interface ContaRepository {
    Conta save(Conta conta);
}
