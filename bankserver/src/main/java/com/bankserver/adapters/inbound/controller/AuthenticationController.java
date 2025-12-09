package com.bankserver.adapters.inbound.controller;

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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data, HttpServletResponse response) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());

        // DEBUG: Mostra a classe real do AuthenticationManager
        System.out.println("AuthenticationManager class: " +
                this.authenticationManager.getClass().getName());

        // * DaoAuthenticationProvider é o provider padrão para autenticação com
        // username/senha

        // manager delega para o AuthorizationService!
        var auth = this.authenticationManager.authenticate(usernamePassword);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        Usuario usuario = userDetails.getUsuario();

        var token = tokenService.generateToken(usuario);

        // criar cookie HttpOnly
        // Cookie cookie = new Cookie("jwt", token);
        // cookie.setHttpOnly(true);
        // cookie.setSecure(true); // apenas HTTPS em produção
        // cookie.setPath("/");
        // cookie.setMaxAge(2 * 60 * 60); // 2 horas em segundos
        // cookie.setAttribute("SameSite", "Lax");
        boolean isProduction = System.getenv("RENDER") != null;

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(2 * 60 * 60);

        if (isProduction) {
            // PRODUÇÃO (Render): HTTPS + SameSite=None
            cookie.setSecure(true);
            cookie.setAttribute("SameSite", "None");
        } else {
            // LOCAL: HTTP + SameSite=Lax
            cookie.setSecure(false);
            cookie.setAttribute("SameSite", "Lax");
        }

        response.addCookie(cookie);

        // Retornar usuário sem o token no body
        return ResponseEntity.ok(new LoginResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getPerfil().name()));
    }

}
