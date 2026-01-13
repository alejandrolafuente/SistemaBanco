package com.bankserver.application.usecases;

import org.springframework.http.ResponseEntity;

import com.bankserver.application.queries.BuscarSolicitacoesQuery;

public interface GerenteServicePort {

    // R09 - tela inicial gerente
    ResponseEntity<?> solicitacoesPendentes(BuscarSolicitacoesQuery query);

    // R10 - aprovar cliente (aprovar conta)
    ResponseEntity<Void> aprovarCliente(Long contaId);
}
