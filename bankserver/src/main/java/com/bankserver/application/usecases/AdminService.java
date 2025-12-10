package com.bankserver.application.usecases;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.AdminRegistrationDTO;
import com.bankserver.dto.request.GerenteRegistrationDTO;

public interface AdminService {

    ResponseEntity<Void> insertGerente(GerenteRegistrationDTO data);
    
    ResponseEntity<Void> insertAdmin(AdminRegistrationDTO data);

}
