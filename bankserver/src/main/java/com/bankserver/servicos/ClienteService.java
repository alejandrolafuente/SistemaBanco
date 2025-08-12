package com.bankserver.servicos;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.ClienteRegistrationDTO;

public interface ClienteService {

    ResponseEntity<?> insertClient(ClienteRegistrationDTO data); 
}
