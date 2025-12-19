package com.bankserver.adapters.outbound.ports;

import java.util.List;


import com.bankserver.application.domain.Gerente;

public interface GerenteRepository {

    Gerente save(Gerente gerente);

    List<Gerente> findAllOrderByQuantidadeContas();
}
