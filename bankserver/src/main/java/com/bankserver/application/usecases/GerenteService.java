package com.bankserver.application.usecases;

import org.springframework.http.ResponseEntity;

public interface GerenteService {

    // R09 - tela inicial gerente
    ResponseEntity<?> solicitacoesPendentes(Long gerenteId);

    // R10 - aprovar cliente (aprovar conta)
    ResponseEntity<Void> aprovarCliente(Long contaId);
}
