package com.bankserver.servicos;

import org.springframework.http.ResponseEntity;

public interface GerenteService {

    // R09
    ResponseEntity<?> solicitacoesPendentes(Long id);

    // R10
    ResponseEntity<?> aprovarCliente(Long contaId);
}
