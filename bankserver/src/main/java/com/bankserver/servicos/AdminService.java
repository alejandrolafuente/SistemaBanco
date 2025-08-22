package com.bankserver.servicos;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.AdminRegistrationDTO;
import com.bankserver.dto.request.GerenteRegistrationDTO;

public interface AdminService {

    ResponseEntity<?> insertGerente(GerenteRegistrationDTO data);
    
    ResponseEntity<?> insertAdmin(AdminRegistrationDTO data);

}
