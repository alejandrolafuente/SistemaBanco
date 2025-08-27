package com.bankserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.servicos.GerenteService;

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

    @PostMapping("/aprovar-conta/{contaId}")
    public ResponseEntity<?> aprovarConta(@PathVariable Long gerenteId, @PathVariable Long contaId) {
        // gerenteService.aprovarCliente(contaId); // 👈 Repassa para o Service
        return ResponseEntity.ok().build();
    }

}
