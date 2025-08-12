package com.bankserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.dto.request.ClienteRegistrationDTO;
import com.bankserver.servicos.ClienteService;

@RestController
@RequestMapping("cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/register")
    @SuppressWarnings("rawtypes")
    public ResponseEntity register(@RequestBody ClienteRegistrationDTO dto) {

        clienteService.insertClient(dto);

        return ResponseEntity.ok().build();
    }
}
