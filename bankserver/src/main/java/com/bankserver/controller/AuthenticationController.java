package com.bankserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankserver.dto.request.AuthenticationDTO;
import com.bankserver.dto.response.LoginResponseDTO;
import com.bankserver.model.Usuario;
import com.bankserver.seguranca.TokenService;
import com.bankserver.seguranca.UserDetailsImpl;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {

        System.out.println("CHEGOU DO FRONT: " + data);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // correção: acessar o Usuario através do UserDetailsImpl!
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Usuario usuario = userDetails.getUsuario(); // coloquei este método na UserDetailsImpl

        System.out.println("BIRULEIBI: " + usuario.getCpf());

        var token = tokenService.generateToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(
                usuario.getId(),
                token,
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getPerfil().name()));
    }

}
