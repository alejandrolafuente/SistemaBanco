package com.bankserver.adapters.inbound.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.adapters.outbound.repository.JpaUsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UtilController {

    @Autowired
    private JpaUsuarioRepository usuarioRepository;

    @GetMapping("/verificar-email/{email}")
    public ResponseEntity<Map<String, Boolean>> verificarEmail(@PathVariable String email) {
        boolean existe = usuarioRepository.existsByLogin(email);
        return ResponseEntity.ok(Map.of("existe", existe));
    }

    @GetMapping("/verificar-cpf/{cpf}")
    public ResponseEntity<Map<String, Boolean>> verificarCpf(@PathVariable String cpf) {
        boolean existe = usuarioRepository.existsByCpf(cpf);
        return ResponseEntity.ok(Map.of("existe", existe));
    }
}
