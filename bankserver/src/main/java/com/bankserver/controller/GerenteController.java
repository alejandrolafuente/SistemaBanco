package com.bankserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.dto.request.GerenteRegistrationDTO;
import com.bankserver.servicos.GerenteService;

@RestController
@RequestMapping("gerentes")
@CrossOrigin
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody GerenteRegistrationDTO dto) {

        return gerenteService.insertGerente(dto);

    }

}
