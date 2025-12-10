package com.bankserver.application.usecases;

import org.springframework.http.ResponseEntity;

public interface GerenteService {

    // R09
    ResponseEntity<?> solicitacoesPendentes(Long id);

    // R10
    ResponseEntity<Void> aprovarCliente(Long contaId);
}
