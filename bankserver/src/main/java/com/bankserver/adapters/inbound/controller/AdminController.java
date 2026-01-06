package com.bankserver.adapters.inbound.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.application.commands.CriarAdminCommand;
import com.bankserver.application.commands.CriarGerenteCommand;
import com.bankserver.application.domain.Administrador;
import com.bankserver.application.domain.Gerente;
import com.bankserver.application.usecases.AdminServicePort;
import com.bankserver.dto.request.AdminRegistrationDTO;
import com.bankserver.dto.request.GerenteRegistrationDTO;
import com.bankserver.dto.response.AdminResponseDTO;
import com.bankserver.dto.response.GerenteResponseDTO;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

    private final AdminServicePort adminServicePort;

    public AdminController(AdminServicePort adminServicePort) {
        this.adminServicePort = adminServicePort;
    }

    // R17 - cadastrar gerente
    @PostMapping("/novo-gerente")
    @Transactional
    public ResponseEntity<GerenteResponseDTO> novoGerente(@RequestBody GerenteRegistrationDTO request) {

        // 1. converte dto da api para command do core
        CriarGerenteCommand command = new CriarGerenteCommand(
                request.cpf(),
                request.email(),
                request.nome(),
                request.telefone());

        Gerente gerenteCriado = adminServicePort.criarGerente(command);

        GerenteResponseDTO response = new GerenteResponseDTO(
                gerenteCriado.getId(),
                gerenteCriado.getNome(),
                gerenteCriado.getCpf(),
                gerenteCriado.getLogin(),
                gerenteCriado.getTelefone());

        return ResponseEntity.status(201).body(response);
    }

    // R21 - cadastrar admin
    @PostMapping
    public ResponseEntity<AdminResponseDTO> criarAdmin(@RequestBody AdminRegistrationDTO request) {

        // 1. converte dto da api para command do core
        CriarAdminCommand command = new CriarAdminCommand(
                request.cpf(),
                request.email(),
                request.nome(),
                request.telefone());

        // 2. chama a porta do core
        Administrador adminCriado = adminServicePort.criarAdmin(command);

        // 3. Converte resultado para dto da api
        AdminResponseDTO response = new AdminResponseDTO(
                adminCriado.getId(),
                adminCriado.getNome(),
                adminCriado.getCpf(),
                adminCriado.getLogin(),
                adminCriado.getTelefone());

        return ResponseEntity.status(201).body(response);

    }

}
