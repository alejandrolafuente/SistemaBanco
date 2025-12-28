package com.bankserver.adapters.outbound.ports;

import com.bankserver.application.domain.Saldo;

public interface SaldoRepository {

    Saldo save(Saldo saldo);
}
