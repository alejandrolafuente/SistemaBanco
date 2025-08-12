package com.bankserver.servicos;

import com.bankserver.dto.request.ClienteRegistrationDTO;

public interface ClienteService {

    Integer insertClient(ClienteRegistrationDTO data); // retornar um por enquanto
}
