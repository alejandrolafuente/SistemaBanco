package com.bankserver.adapters.outbound.ports;

import com.bankserver.application.domain.Endereco;

public interface EnderecoRepository {
    
    Endereco save(Endereco endereco);
}
