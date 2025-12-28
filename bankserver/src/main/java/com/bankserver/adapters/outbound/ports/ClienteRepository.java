package com.bankserver.adapters.outbound.ports;

import com.bankserver.application.domain.Cliente;

public interface ClienteRepository {

    Cliente save(Cliente cliente);

    Cliente update(Cliente cliente);
}
