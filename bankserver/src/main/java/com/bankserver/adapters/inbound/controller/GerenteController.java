package com.bankserver.adapters.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.application.usecases.GerenteService;

@RestController
@RequestMapping("gerentes")
@CrossOrigin
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    // R09 - Tela Inicial do Gerente
    @GetMapping("/{id}/solicitacoes-pendentes")
    public ResponseEntity<?> solicitacoesPendentes(@PathVariable Long id) {
        return gerenteService.solicitacoesPendentes(id);
    }

    // R10 - Aprovar Cliente
    @PutMapping("/aprovar-conta/{contaId}")
    public ResponseEntity<?> aprovarConta(@PathVariable Long contaId) {
        return gerenteService.aprovarCliente(contaId);

    }

}
