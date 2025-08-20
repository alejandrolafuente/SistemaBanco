package com.bankserver.servicos;

import org.springframework.http.ResponseEntity;

import com.bankserver.dto.request.AdminRegistrationDTO;

public interface AdminService {

    ResponseEntity<?> insertAdmin(AdminRegistrationDTO data);
}
