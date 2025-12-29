package com.bankserver.adapters.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.application.usecases.AdminServicePort;
import com.bankserver.dto.request.AdminRegistrationDTO;
import com.bankserver.dto.request.GerenteRegistrationDTO;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminServicePort adminServicePort;

    // R17 - cadastrar gerente
    @PostMapping("/novo-gerente")
    @Transactional
    public ResponseEntity<?> novoGerente(@RequestBody GerenteRegistrationDTO dto) {

        return adminServicePort.insertGerente(dto);

    }

    // R21 - cadastrar admin
    @PostMapping
    @Transactional
    public ResponseEntity<Void> register(@RequestBody AdminRegistrationDTO dto) {

        return adminServicePort.insertAdmin(dto);

    }

}
