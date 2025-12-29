package com.bankserver.application.usecases;

import org.springframework.http.ResponseEntity;

import com.bankserver.application.commands.CriarAdminCommand;
import com.bankserver.application.domain.Administrador;
import com.bankserver.dto.request.GerenteRegistrationDTO;

public interface AdminServicePort {

    // R17
    ResponseEntity<Void> insertGerente(GerenteRegistrationDTO data);

    // R21 - Cadastrar ADMIN
    Administrador criarAdmin(CriarAdminCommand command);

}
