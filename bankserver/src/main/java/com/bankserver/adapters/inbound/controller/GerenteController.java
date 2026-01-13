package com.bankserver.adapters.inbound.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.application.queries.BuscarSolicitacoesQuery;
import com.bankserver.application.usecases.GerenteServicePort;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("gerentes")
@CrossOrigin
public class GerenteController {

    private final GerenteServicePort gerenteServicePort;

    public GerenteController(GerenteServicePort gerenteServicePort) {
        this.gerenteServicePort = gerenteServicePort;
    }

    // R09 - tela inicial gerente
    @GetMapping("/{gerenteId}/solicitacoes-pendentes")
    public ResponseEntity<?> solicitacoesPendentes(@PathVariable Long gerenteId) {

        BuscarSolicitacoesQuery query = new BuscarSolicitacoesQuery(gerenteId);
       // return gerenteServicePort.solicitacoesPendentes(gerenteId);
       return null;
    }

    // R10 - aprovar cliente (aprovar conta)
    @PutMapping("/aprovar-conta/{contaId}")
    @Transactional
    public ResponseEntity<?> aprovarConta(@PathVariable Long contaId) {
        return gerenteServicePort.aprovarCliente(contaId);

    }

}
