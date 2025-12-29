package com.bankserver.application.usecases;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.AdminRegistrationDTO;
import com.bankserver.dto.request.GerenteRegistrationDTO;

public interface AdminServicePort {

    // R17
    ResponseEntity<Void> insertGerente(GerenteRegistrationDTO data);

    // R21 - Cadastrar ADMIN
    ResponseEntity<Void> insertAdmin(AdminRegistrationDTO data);

}
