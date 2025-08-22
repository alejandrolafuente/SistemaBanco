package com.bankserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.dto.request.AdminRegistrationDTO;
import com.bankserver.dto.request.GerenteRegistrationDTO;
import com.bankserver.servicos.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    // R17
    @PostMapping("/novo-gerente")
    public ResponseEntity<?> novoGerente(@RequestBody GerenteRegistrationDTO dto) {

        return adminService.insertGerente(dto);

    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody AdminRegistrationDTO dto) {

        return adminService.insertAdmin(dto);

    }

}
