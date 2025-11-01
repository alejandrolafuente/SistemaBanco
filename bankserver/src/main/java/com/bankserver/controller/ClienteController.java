package com.bankserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.dto.request.DepositoDTO;
import com.bankserver.dto.request.SaqueDTO;
import com.bankserver.servicos.ClienteService;

@RestController
@RequestMapping("cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // R01
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClienteRegistrationDTO dto) {
        return clienteService.insertClient(dto);
    }

    // R03
    @GetMapping("/saldo/{userId}")
    public ResponseEntity<?> buscaSaldo(@PathVariable Long userId) {

        return clienteService.buscaSaldo(userId);
    }

    // R05
    @PostMapping("/deposito")
    public ResponseEntity<?> deposito(@RequestBody DepositoDTO dto) {
        return clienteService.realizarDeposito(dto);
    }

    // R06
    @PostMapping("/saque")
    public ResponseEntity<?> saque(@RequestBody SaqueDTO dto) {
        return clienteService.realizarSaque(dto);
    }

}
