package com.bankserver.servicos;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.GerenteRegistrationDTO;

public interface GerenteService {

    ResponseEntity<?> insertGerente(GerenteRegistrationDTO data);
}
