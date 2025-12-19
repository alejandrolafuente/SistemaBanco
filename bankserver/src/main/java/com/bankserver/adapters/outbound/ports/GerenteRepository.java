package com.bankserver.adapters.outbound.ports;

import com.bankserver.application.domain.Gerente;

public interface GerenteRepository {

    Gerente save(Gerente gerente);
}
