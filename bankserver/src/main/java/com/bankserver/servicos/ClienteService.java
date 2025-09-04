package com.bankserver.servicos;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.ClienteRegistrationDTO;

public interface ClienteService {

    // R01
    ResponseEntity<?> insertClient(ClienteRegistrationDTO data);

    // R03
    ResponseEntity<?> buscaSaldo(Long userId);
}
