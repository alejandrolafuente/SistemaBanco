package com.bankserver.adapters.outbound.adapters;

import org.springframework.stereotype.Repository;

import com.bankserver.adapters.outbound.ports.ClienteRepository;
import com.bankserver.application.domain.Cliente;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public Cliente save(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
